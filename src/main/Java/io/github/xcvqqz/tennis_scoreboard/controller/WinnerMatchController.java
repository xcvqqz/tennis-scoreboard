package io.github.xcvqqz.tennis_scoreboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet("/winner-match")
    public class WinnerMatchController extends BasicController {

        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            String playerWinner = request.getParameter("playerWinner");
            request.setAttribute("playerWinner", playerWinner);
            forwardToWinnerMatch(request, response);

        }
    }