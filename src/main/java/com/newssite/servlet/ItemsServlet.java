package com.newssite.servlet;

import com.newssite.db.DataAccess;
import com.newssite.model.Details;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/item")
public class ItemsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)

        throws IOException {
        try
        {
                if (DataAccess.CheckIdnum("Posts", "ID", Integer.parseInt(request.getParameter("id"))))
                {
                    System.out.println("Valied id and goes to /jsp/items.jsp");
                    Details post = new DataAccess().getPost(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("abc",post);
                    request.getRequestDispatcher("/jsp/items.jsp").forward(request, response);
                }
                else {
                    System.out.println("Item id ("+ request.getParameter("id")+")is invalied");
                    response.getWriter().println("Error!......");
                    response.getWriter().println("Invalied Post");
                }
            } catch (ServletException | SQLException e) {
                e.printStackTrace();
            }
    }
}
