package controller;

import dao.PlayerDAO;
import dto.MatchDTO;
import dto.PlayerDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Match;
import model.Player;
import service.OngoingMatchesService;
import util.UUIDUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {


    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final UUIDUtil uuidUtil = new UUIDUtil();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/new-match.jsp").forward(request, response);

    }



    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String playerOneName = request.getParameter("playerOneName");
        String playerTwoName = request.getParameter("playerTwoName");

        PlayerDTO playerOne = new PlayerDTO(playerOneName);
        PlayerDTO playerTwo = new PlayerDTO(playerTwoName);


        MatchDTO newMatch = new MatchDTO(playerOne, playerTwo);
        ongoingMatchesService.addNewMatch(newMatch);

        request.setAttribute("uuid", uuidUtil.getUUID());

        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + uuidUtil.getUUID());


//        Проверяет существование игроков в таблице Players. Если игрока с таким именем не существует, то создаём
//        Создаём экземпляр класса, содержащего айди игроков и текущий счёт,
//        и кладём в коллекцию текущих матчей (существующую только в памяти приложения, либо в key-value storage).
//        Ключом коллекции является UUID, значением - счёт в матче
//        Редирект на страницу /match-score?uuid=$match_id

    }






}