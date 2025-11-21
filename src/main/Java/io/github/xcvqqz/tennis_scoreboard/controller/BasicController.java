package io.github.xcvqqz.tennis_scoreboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import io.github.xcvqqz.tennis_scoreboard.service.FinishedMatchesPersistenceService;
import io.github.xcvqqz.tennis_scoreboard.service.OngoingMatchesService;
import io.github.xcvqqz.tennis_scoreboard.service.PlayerService;
import io.github.xcvqqz.tennis_scoreboard.util.UUIDUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class BasicController extends HttpServlet {

    protected final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected final UUIDUtil uuidUtil = new UUIDUtil();
    protected final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    protected final PlayerService playerService = new PlayerService();

    protected void forwardToNewMatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/new-match.jsp").forward(request, response);
    }

    protected void sendRedirectToMatchScore(HttpServletRequest request, HttpServletResponse response, UUID uuid) throws IOException {
        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + uuid);
    }

    protected void forwardToMatchScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }

    protected void forwardToFinishedMatches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }

    protected void sendRedirectToWinnerMatch(HttpServletRequest request, HttpServletResponse response, String playerName) throws  IOException {
        response.sendRedirect(request.getContextPath() + "/winner-match?playerWinner=" +
                URLEncoder.encode(playerName, StandardCharsets.UTF_8));
    }

    protected void forwardToWinnerMatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/winner-match.jsp").forward(request, response);
    }

    protected void forwardToIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}