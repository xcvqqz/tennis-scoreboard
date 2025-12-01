package io.github.xcvqqz.tennis_scoreboard.filter;

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
        }
    }