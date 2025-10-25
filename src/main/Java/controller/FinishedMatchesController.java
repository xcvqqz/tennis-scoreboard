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

        int page = Integer.parseInt(request.getParameter("page"));                  //currentPage
        String playerName = request.getParameter("filter_by_player_name");

        List<Match> matches;


        if (playerName == null || playerName.trim().isEmpty()) {
            //Если параметр не задан, отображаются все матчи

            matches = matchService.findAllMatches();
        }


        PaginationResponseDTO paginationResponseDTO = finishedMatchesPersistenceService.getFinishedMatches(playerName, page);


    }


    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


    }


}