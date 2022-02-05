package com.newssite.servlet;

import com.newssite.db.DataAccess;
import com.newssite.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/upvote")
public class AddVoteServ extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        System.out.println(".................In AddVoteServ servlet................");
        int PostID = Integer.parseInt(req.getParameter("id"));
        System.out.println("Post ID  ::  " + PostID);
        int  userID = (Integer) req.getAttribute("validateduserid");
        try {
            if (DataAccess.CheckIdnum("Posts", "ID", PostID))
            {
                System.out.println("Valied Id.... Checking Vote Type.....");
                DataAccess.AddVote(PostID, userID);
                System.out.println("All post list...");
                List<Post> posts = DataAccess.getPosts();
                req.setAttribute("postsList", posts);
                req.getRequestDispatcher("/jsp/posts.jsp").forward(req, resp);
            }
            else
            {
                resp.getWriter().println("invalied id's....");
            }
        } catch (SQLException | ServletException ex) {
            ex.printStackTrace();
        }
    }
}