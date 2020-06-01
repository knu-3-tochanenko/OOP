package com.tochanenko.database;

import com.tochanenko.entities.Ride;
import com.tochanenko.entities.User;
import com.tochanenko.utils.SqlFileLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RideDao {
    public static List<Ride> getRides() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Rides";

        ResultSet resultSet = statement.executeQuery(query);

        List<Ride> rides = new ArrayList<>();

        while (resultSet.next()) {
            rides.add(new Ride(
                    resultSet.getInt("id"),
                    resultSet.getInt("car_id"),
                    resultSet.getInt("booking_id"),
                    resultSet.getInt("cost")
            ));
        }

        resultSet.close();
        connection.close();

        return rides;
    }

    public static Ride getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Rides WHERE id=" + id;

        ResultSet resultSet = statement.executeQuery(query);

        Ride ride = null;

        if (resultSet.next()) {
            int carId = resultSet.getInt("car_id");
            int bookingId = resultSet.getInt("booking_id");
            int cost = resultSet.getInt("cost");
            ride = new Ride(id, carId, bookingId, cost);
        }

        resultSet.close();
        connection.close();

        return ride;
    }

    public static int getCostForBooking(int bookingId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Rides WHERE booking_id=" + bookingId;

        ResultSet resultSet = statement.executeQuery(query);

        Ride ride = null;

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            int carId = resultSet.getInt("car_id");
            int cost = resultSet.getInt("cost");
            ride = new Ride(id, carId, bookingId, cost);
        }

        resultSet.close();
        connection.close();

        return ride != null ? ride.getCost() : -1;
    }

    public static int getEarningsForUser(int userId) throws SQLException, ClassNotFoundException {
        User user = UserDao.getById(userId);
        Connection connection = DBConnection.initDB();

        String query = SqlFileLoader.load("get_earnings_for_user.sql");
        System.out.println("EARNING QUERY :\n" + query);

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getCarId());

        ResultSet resultSet = statement.executeQuery();

        int res = 0;

        if (resultSet.next()) {
            res = resultSet.getInt("earnings");
        }

        resultSet.close();
        connection.close();

        return res;
    }

    public static void insert(Ride ride) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Rides (id, car_id, booking_id, cost) VALUES(DEFAULT, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ride.getCarId());
        statement.setInt(2, ride.getBookingId());
        statement.setInt(3, ride.getCost());

        statement.executeUpdate();
        connection.close();
    }
}
