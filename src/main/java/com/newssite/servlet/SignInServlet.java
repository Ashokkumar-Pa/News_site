package com.newssite.servlet;

import com.newssite.db.DataAccess;
import com.newssite.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("signin servlet called ");
        try {
            if (DataAccess.isUserValid(request.getParameter("name")) == null)
            {
                System.out.println(request.getParameter("name") + " this user name is valied user name");
                if (DataAccess.AddNewUserName(request.getParameter("name")))
                {
                    System.out.println("New User added ");
                    Cookie cookie = new Cookie("loggeginuser", request.getParameter("name"));
                    cookie.setMaxAge(24 * 60 * 60 );
                    response.addCookie(cookie);
                    System.out.println(cookie.getName() + " and its value is " + cookie.getValue() + " and expires on " + cookie.getMaxAge());
                    response.sendRedirect("/sample/");
                    String url = request.getRequestURI();
                    System.out.println(url);
                    return;
                }

            }
            else {
                System.out.println(request.getParameter("name") + " this user name is already taken");
                response.sendRedirect("/sample");
                return;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
