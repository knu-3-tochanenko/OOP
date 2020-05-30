package com.tochanenko.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({ "/", "/home"})
public class HomepageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContextPath());
        System.out.println("--------------!!!!!!!!!!!!!!!-----------------");
//        PrintWriter writer = response.getWriter();
//        writer.print("<b>something something</b>");
        request.getRequestDispatcher("index.jsp").forward(request, response);


    }
}
