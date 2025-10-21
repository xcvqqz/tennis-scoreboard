package controller;

import dto.MatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchScoreCalculationService;

import java.io.IOException;
import java.util.UUID;


//сначала переходим на первую страницу matches (ловим через фильтр)

//потом уже принимаем page и filter_by_player_name, потом вызываем снова GET запрос

//реализация пагинации


@WebServlet("/matches")
public class MatchesController extends BasicController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String page = request.getParameter("page");
        String filterByPlayerName = request.getParameter("filter_by_player_name");



    }


    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


    }


}