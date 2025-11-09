package io.github.xcvqqz.tennis_scoreboard.filter;

import io.github.xcvqqz.tennis_scoreboard.exception.BadRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter("/matches")
public class MatchesParamFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

try {
        HttpServletRequest requestWrapper = new HttpServletRequestWrapper(request) {

            @Override
            public String getParameter(String name) {

                if (name.equals("filter_by_player_name")) {
                    String filterPlayerName = super.getParameter("filter_by_player_name");
                    return (filterPlayerName == null) ? "" : filterPlayerName;
                }

                if (name.equals("page")) {
                    String page = super.getParameter("page");
                    return (page == null) ? "1" : page;
                }
                return super.getParameter(name);
            }
        };
               filterChain.doFilter(requestWrapper, response);
            } catch (BadRequestException e) {
                sendError(request, response, e, HttpServletResponse.SC_BAD_REQUEST);
            }
        }


    private void sendError(HttpServletRequest request, HttpServletResponse response, Exception e, int status) throws IOException, ServletException {
        response.setStatus(status);
        request.setAttribute("errorMessage", e.getMessage());
        request.setAttribute("statusCode", status);
        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }
}






//        String page = request.getParameter("page");
//        String filterByPlayerName = request.getParameter("filter_by_player_name");
//
//    try {
//
//        if(page != null && filterByPlayerName != null){
//            response.sendRedirect(request.getContextPath() + "/matches?page=" + page + "&filter_by_player_name=" + filterByPlayerName);
//            return;
//        }
//
//        if(filterByPlayerName == null){
//            filterByPlayerName = "";
//        }
//
//        if(page == null){
//            page = "1";
//        }
//
//
//    }