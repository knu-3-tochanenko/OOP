package com.tochanenko.servlet;

import com.tochanenko.database.UserDao;
import com.tochanenko.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({ "/", "/home"})
public class HomepageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = null;

        try {
            user = UserDao.getByLogin(email, password);
            if (user == null) {
                response.sendRedirect("/error?message=\"Invalid email or password!\"");
            } else {
                System.out.println("Prepared to send user data: " + user.getId() + " and " + user.getRole());
                response.sendRedirect("/config?role=" + user.getRole() + "&id=" + user.getId());
                System.out.println(user.getId() + " : " + user.getRole());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
