package com.tochanenko.servlet;

import com.tochanenko.database.AutomobileDao;
import com.tochanenko.database.BookingDao;
import com.tochanenko.entities.Automobile;
import com.tochanenko.entities.RideStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/inspect_automobile")
public class InspectAutomobileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int automobileId = Integer.parseInt(request.getParameter("id"));

        try {
            AutomobileDao.inspect(automobileId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/dashboard");
    }
}
