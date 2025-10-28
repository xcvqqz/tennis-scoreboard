package io.github.xcvqqz.tennis_scoreboard.controller;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.PlayerMapper;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/new-match")
public class NewMatchController extends BasicController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/new-match.jsp").forward(request, response);
    }


    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String playerOneName = request.getParameter("playerOneName");
        String playerTwoName = request.getParameter("playerTwoName");

        PlayerDTO playerOne = PlayerMapper.INSTANCE.toDTO(playerService.createPlayerIfNotExists(playerOneName));
        PlayerDTO playerTwo = PlayerMapper.INSTANCE.toDTO(playerService.createPlayerIfNotExists(playerTwoName));

        UUID uuid = uuidUtil.getNewUUID();

        
        MatchDTO newMatch = new MatchDTO(playerOne, playerTwo);

        ongoingMatchesService.addNewOngoingMatches(uuid, newMatch);

        request.setAttribute("uuid", uuid);

        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + uuid);


//        Проверяет существование игроков в таблице Players. Если игрока с таким именем не существует, то создаём
//        Создаём экземпляр класса, содержащего айди игроков и текущий счёт,
//        и кладём в коллекцию текущих матчей (существующую только в памяти приложения, либо в key-value storage).
//        Ключом коллекции является UUID, значением - счёт в матче
//        Редирект на страницу /match-score?uuid=$match_id

    }






}