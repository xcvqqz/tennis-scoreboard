package io.github.xcvqqz.tennis_scoreboard.controller;


import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import io.github.xcvqqz.tennis_scoreboard.service.MatchScoreCalculationService;
import java.io.IOException;
import java.util.UUID;


@WebServlet("/match-score")
public class MatchScoreController extends BasicController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UUID uuid = uuidUtil.parseUUID(request.getParameter("uuid"));
        MatchDTO match = ongoingMatchesService.getOngoingMatch(uuid);

        request.setAttribute("match", match);
        request.setAttribute("uuid", uuid);

        forwardToMatchScore(request, response);
    }

    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String playerName = request.getParameter("playerName");
        UUID uuid = uuidUtil.parseUUID(request.getParameter("uuid"));

        MatchDTO match = ongoingMatchesService.getOngoingMatch(uuid);
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(match);

        boolean isPlayerOne = playerName.equals(match.getPlayerOne().getName());
        matchScoreCalculationService.addPoint(isPlayerOne ? match.getPlayerOne() : match.getPlayerTwo());

        if(match.isMatchOver()){
            finishedMatchesPersistenceService.save(match);
            ongoingMatchesService.deleteOngoingMatch(match);
            request.setAttribute("playerWinner", playerName);
            sendRedirectToWinnerMatch(request, response, playerName);
        } else {
            sendRedirectToMatchScore(request, response, uuid);
        }
    }
}