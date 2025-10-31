package io.github.xcvqqz.tennis_scoreboard.filter;

import io.github.xcvqqz.tennis_scoreboard.exception.BadRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter({"/new-match", "/matches"})
public class ExceptionHandlingFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(request, response);
        } catch (BadRequestException e){
            sendError(request, response, e, HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    private void sendError(HttpServletRequest request, HttpServletResponse response, Exception e, int status) throws IOException, ServletException {
        response.setStatus(status);
        request.setAttribute("message", e.getMessage());
        request.setAttribute("statusCode", status);
        request.getRequestDispatcher("/new-match.jsp").forward(request, response);
    }

}
