package com.tochanenko.database;

import com.tochanenko.entities.AutoClass;
import com.tochanenko.entities.Booking;
import com.tochanenko.entities.RideStatus;
import com.tochanenko.utils.SqlFileLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    public static Booking getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Bookings WHERE id=" + id;

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

    public static List<Booking> getBookingsForUser(int userId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = SqlFileLoader.load("get_booking_for_user.sql");
        System.out.println("BOOKING QUERY :\n" + query);

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, UserDao.getById(userId).getCarId());

        ResultSet resultSet = statement.executeQuery();

        List<Booking> bookings = new ArrayList<>();

        while (resultSet.next()) {
            bookings.add(new Booking(
                    resultSet.getInt("id"),
                    AutoClass.valueOf(resultSet.getString("min_class")),
                    resultSet.getString("depart"),
                    resultSet.getString("destination"),
                    resultSet.getInt("min_seats"),
                    RideStatus.valueOf(resultSet.getString("status"))
            ));
        }

        resultSet.close();
        connection.close();

        return bookings;
    }

    public static List<Booking> getBookings() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Bookings";

        ResultSet resultSet = statement.executeQuery(query);

        List<Booking> bookings = new ArrayList<>();

        while (resultSet.next()) {
            bookings.add(new Booking(
                    resultSet.getInt("id"),
                    AutoClass.valueOf(resultSet.getString("min_class")),
                    resultSet.getString("depart"),
                    resultSet.getString("destination"),
                    resultSet.getInt("min_seats"),
                    RideStatus.valueOf(resultSet.getString("status"))
            ));
        }

        resultSet.close();
        connection.close();

        return bookings;
    }

    public static void insert(Booking booking) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Bookings (id, depart, destination, min_seats, min_class, status) VALUES(DEFAULT, ?, ?, ?, ?::auto_class, DEFAULT)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, booking.getDepart());
        statement.setString(2, booking.getDestination());
        statement.setInt(3, booking.getMinSeats());
        statement.setString(4, booking.getMinClass().name());

        statement.executeUpdate();
        connection.close();
    }

    public static void setStatus(int id, RideStatus status) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "UPDATE Bookings SET status='" + status.toString() + "' WHERE id=" + id;

        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
