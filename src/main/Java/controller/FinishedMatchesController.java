package controller;

import dto.PaginationResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;

import java.io.IOException;
import java.util.List;


//сначала переходим на первую страницу matches (ловим через фильтр)

//потом уже принимаем page и filter_by_player_name, потом вызываем снова GET запрос

//реализация пагинации


@WebServlet("/matches")
public class FinishedMatchesController extends BasicController {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long page = Long.parseLong(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");

        PaginationResponseDTO paginationResponseDTO = finishedMatchesPersistenceService.getFinishedMatches(playerName, page);

        request.setAttribute("page", page);
        request.setAttribute("playerName", playerName);
        request.setAttribute("matches", paginationResponseDTO.getMatches());
        System.out.println("Found matches: " + paginationResponseDTO.getMatches().size());
        request.setAttribute("totalPages", paginationResponseDTO.getTotalPage());

        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }


    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


    }

}