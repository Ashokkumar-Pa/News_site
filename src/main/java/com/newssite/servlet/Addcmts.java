package com.newssite.servlet;

import com.newssite.db.DataAccess;
import com.newssite.model.Details;
import com.newssite.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/comment")
public class Addcmts extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int len = req.getParameter("cmtcontent").length();
        if (len < (Math.pow(2, 24) - 1 )&& len != 0)
        {
            System.out.println("Comment: "+ req.getParameter("cmtcontent"));
            try {
                if (DataAccess.CheckIdnum("Posts", "ID", Integer.parseInt(req.getParameter("id"))))
                {
                    System.out.println("Id number is crct | id :: " + req.getParameter("id"));
//                    Integer.parseInt(req.getParameter("userID"))
                    int  userID = (Integer) req.getAttribute("validateduserid");
                    if (DataAccess.addCmt("Comments", Integer.parseInt(req.getParameter("id")),userID, req.getParameter("cmtcontent")))
                    {
                        System.out.println("New comment is added and goes to /jsp/items.jsp");
                        Details post = new DataAccess().getPost(Integer.parseInt(req.getParameter("id")));
                        req.setAttribute("abc",post);
                        req.getRequestDispatcher("/jsp/items.jsp").forward(req, resp);
                    }
                }
                else
                {
                    resp.getWriter().println("invalied id's....");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            resp.getWriter().println("invalied Comment(Null or overloaded)");
        }
    }
}