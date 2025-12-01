package io.github.xcvqqz.tennis_scoreboard.controller;

import io.github.xcvqqz.tennis_scoreboard.dto.PaginationResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class FinishedMatchesController extends BasicController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long page = parsePageParameter(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");

        PaginationResponseDTO paginationResponseDTO = finishedMatchesPersistenceService.getFinishedMatches(playerName, page);

        request.setAttribute("page", page);
        request.setAttribute("playerName", playerName);
        request.setAttribute("matches", paginationResponseDTO.matches());
        request.setAttribute("totalPages", paginationResponseDTO.totalPage());

        forwardToFinishedMatches(request, response);
    }

    private Long parsePageParameter(String pageParam) {
        try {
            Long page = Long.parseLong(pageParam);
            return page < 1 ? 1L : page;
        } catch (NumberFormatException e) {
            return 1L;
        }
    }
}