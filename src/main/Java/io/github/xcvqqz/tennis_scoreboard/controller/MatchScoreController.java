package io.github.xcvqqz.tennis_scoreboard.controller;

import io.github.xcvqqz.tennis_scoreboard.domain_model.OngoingMatch;
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
        OngoingMatch ongoingMatch= ongoingMatchesService.getOngoingMatch(uuid);

        request.setAttribute("match", ongoingMatch);
        request.setAttribute("uuid", uuid);

        forwardToMatchScore(request, response);
    }

    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String playerName = request.getParameter("playerName");
        UUID uuid = uuidUtil.parseUUID(request.getParameter("uuid"));

        OngoingMatch ongoingMatch = ongoingMatchesService.getOngoingMatch(uuid);
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(ongoingMatch);

        boolean isPlayerOne = playerName.equals(ongoingMatch.getPlayerOne().getName());

        matchScoreCalculationService.addPoint(isPlayerOne ? ongoingMatch.getPlayerOneScore() : ongoingMatch.getPlayerTwoScore());

        if(ongoingMatch.isMatchOver()){
            finishedMatchesPersistenceService.save(ongoingMatch);
            ongoingMatchesService.deleteOngoingMatch(ongoingMatch);
            request.setAttribute("playerWinner", playerName);
            sendRedirectToWinnerMatch(request, response, playerName);
        } else {
            sendRedirectToMatchScore(request, response, uuid);
        }
    }
}