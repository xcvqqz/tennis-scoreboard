package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebFilter("/matches")
public class MatchesParamFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        if(servletRequest.getParameter("page") == null || servletRequest.getParameter("page").isEmpty()){
            servletRequest.setAttribute("page", 1);
        }

        if(servletRequest.getParameter("filter_by_player_name") == null  || servletRequest.getParameter("filter_by_player_name").isEmpty()){
            servletRequest.setAttribute("filter_by_player_name", "");
        }

            filterChain.doFilter(servletRequest, servletResponse);
        }




}
