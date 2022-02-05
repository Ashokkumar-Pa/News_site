
package com.newssite.servlet;

import com.newssite.db.DataAccess;
import com.newssite.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getpost")
public class GetPostDetaile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("... Submit button is opened ( webservlet /getpost ) (getPostDetaile.java file) ...");
            if (req.getParameter("title") != null && req.getParameter("url") != null)
            {
                System.out.println("Title and url are not null................");
//                Integer.parseInt(req.getParameter("id"))
                try {
                    int  userID = (Integer) req.getAttribute("validateduserid");
                    if (DataAccess.addNewPost(userID , req.getParameter("title"), req.getParameter("url")))
                    {
                        resp.sendRedirect("/sample/");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("...........Upload new post Filed ): ...........");
                resp.getWriter().println("Upload new post Filed ):  .......... ");
            }
    }
}