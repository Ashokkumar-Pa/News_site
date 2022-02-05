package com.newssite.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class NewPostServ extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try
        {
            System.out.println("New post html page is opened......");
            request.getRequestDispatcher("/jsp/addPost.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}