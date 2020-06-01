package com.tochanenko.servlet;

import com.tochanenko.database.AutomobileDao;
import com.tochanenko.database.UserDao;
import com.tochanenko.entities.AutoClass;
import com.tochanenko.entities.Automobile;
import com.tochanenko.entities.User;
import com.tochanenko.entities.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int carId = Integer.parseInt(request.getParameter("user_auto"));
        String name = request.getParameter("user_name");
        String surname = request.getParameter("user_surname");

        try {
            UserDao.insert(new User(
                    0,
                    email,
                    password,
                    carId,
                    name,
                    surname,
                    UserRole.DRIVER
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Automobile> freeAutomobiles = AutomobileDao.getFree();

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/add-user.jsp");
            request.setAttribute("free-automobiles", freeAutomobiles);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
