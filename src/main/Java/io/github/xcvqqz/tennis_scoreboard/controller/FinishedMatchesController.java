package io.github.xcvqqz.tennis_scoreboard.controller;

import io.github.xcvqqz.tennis_scoreboard.dto.PaginationResponseDTO;
import io.github.xcvqqz.tennis_scoreboard.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


//сначала переходим на первую страницу matches (ловим через фильтр)

//потом уже принимаем page и filter_by_player_name, потом вызываем снова GET запрос

//реализация пагинации


@WebServlet("/matches")
public class FinishedMatchesController extends BasicController {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long page = Long.parseLong(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");

        Validator.validate(playerName);

        PaginationResponseDTO paginationResponseDTO = finishedMatchesPersistenceService.getFinishedMatches(playerName, page);

        Validator.validate(page, paginationResponseDTO.getTotalPage());

        request.setAttribute("page", page);
        request.setAttribute("playerName", playerName);
        request.setAttribute("matches", paginationResponseDTO.getMatches());
        request.setAttribute("totalPages", paginationResponseDTO.getTotalPage());

        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }


    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

}