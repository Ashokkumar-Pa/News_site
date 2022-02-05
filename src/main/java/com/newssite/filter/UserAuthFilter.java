package com.newssite.filter;

import com.newssite.db.DataAccess;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/*")
public class UserAuthFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    System.out.println("________.................Filter called.................________");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        System.out.println(httpServletRequest  + "  ::  " + requestURI );
        Cookie[] cookies = httpServletRequest.getCookies();
        String loggedInUser = null;
        if(cookies != null)
        {
            for (Cookie cookie : cookies) {
                System.out.println("Cookies  Name ::  " + cookie.getName() + "  value :: " + cookie.getValue());
                if (cookie.getName().equals("loggeginuser")) {
                    loggedInUser = cookie.getValue();
                    break;
                }
            }
        }
        try {
            Integer userID = DataAccess.isUserValid(loggedInUser);
            if(userID != null || requestURI.contains("/sample/signin"))
            {
                request.setAttribute("validateduserid", userID);
                System.out.println("user Name is ' " + loggedInUser + " ' and ID number is ::  " + userID);
                chain.doFilter(request, response);
            }
            else
            {
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                System.out.println(requestURI);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void destroy() {

    }
}
