package com.tochanenko.database;

import com.tochanenko.entities.AutoClass;
import com.tochanenko.entities.Booking;
import com.tochanenko.entities.RideStatus;

import java.sql.*;

public class BookingDao {
    public Booking getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM bookings WHERE id=" + id;

        ResultSet resultSet = statement.executeQuery(query);

        Booking booking = null;

        if (resultSet.next()) {
            AutoClass minClass = AutoClass.valueOf(resultSet.getString("min_class"));
            String depart = resultSet.getString("depart");
            String destination = resultSet.getString("destination");
            int minSeats = resultSet.getInt("min_seats");
            RideStatus status = RideStatus.valueOf(resultSet.getString("status"));
            booking = new Booking(id, minClass, depart, destination, minSeats, status);
        }

        resultSet.close();
        connection.close();

        return booking;
    }

    public void insert(Booking booking) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Bookings (id, min_class, depart, destination, min_seats, status) VALUES(DEFAULT, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, booking.getMinClass().toString());
        statement.setString(2, booking.getDepart());
        statement.setString(3, booking.getDestination());
        statement.setInt(4, booking.getMinSeats());
        statement.setString(5, booking.getStatus().toString());

        statement.executeUpdate();
        connection.close();
    }

    public void setStatus(int id, RideStatus status) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "UPDATE Bookings SET status='" + status.toString() + "' WHERE id=" + id;

        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
