package com.newssite.servlet;

import com.newssite.db.DataAccess;
import com.newssite.model.Post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class PostsServlet extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
                try {
                        System.out.println("________.................In PostServlet.java.................________");
                        List<Post> posts = DataAccess.getPosts();
                        request.setAttribute("postsList", posts);
                        request.getRequestDispatcher("/jsp/posts.jsp").forward(request, response);
                } catch (ServletException | SQLException e) {
                        e.printStackTrace();
                }
        }
}