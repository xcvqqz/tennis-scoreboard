package io.github.xcvqqz.tennis_scoreboard.controller;

import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.entity.Player;
import io.github.xcvqqz.tennis_scoreboard.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends BasicController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        forwardToNewMatch(request, response);
    }

    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String playerOneName = Player.normalizeName(request.getParameter("playerOneName"));
        String playerTwoName = Player.normalizeName(request.getParameter("playerTwoName"));

        Validator.validate(playerOneName);
        Validator.validate(playerTwoName);
        Validator.validateNamesUniqueness(playerOneName, playerTwoName);

        UUID uuid = uuidUtil.getNewUUID();

        PlayerDTO onePlayerDTO = playerService.createOrGetPlayerDTO(playerOneName);
        PlayerDTO twoPlayerDTO = playerService.createOrGetPlayerDTO(playerTwoName);

        ongoingMatchesService.createNewOngoingMatch(uuid, onePlayerDTO, twoPlayerDTO);

        request.setAttribute("uuid", uuid);

        sendRedirectToMatchScore(request, response, uuid);
    }
}