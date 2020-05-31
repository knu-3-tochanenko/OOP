package com.tochanenko.database;

import com.tochanenko.entities.User;
import com.tochanenko.entities.UserRole;

import java.sql.*;

public class UserDao {
    public static User getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Users WHERE id=" + id;

        ResultSet resultSet = statement.executeQuery(query);

        User user = null;

        if (resultSet.next()) {
            int carId = resultSet.getInt("car_id");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            UserRole role = UserRole.valueOf(resultSet.getString("role"));
            user = new User(id, email, password, carId, name, surname, role);
        }

        resultSet.close();
        connection.close();

        return user;
    }

    public static User getByLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "SELECT * FROM Users WHERE email=? and password=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        User user = null;

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            int carId = resultSet.getInt("car_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            UserRole role = UserRole.valueOf(resultSet.getString("role"));
            user = new User(id, email, password, carId, name, surname, role);
        }

        resultSet.close();
        connection.close();

        return user;
    }

    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Users (id, email, password, car_id, name, surname, role) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getCarId());
        statement.setString(4, user.getName());
        statement.setString(5, user.getSurname());
        statement.setString(6, user.getRole().toString());

        statement.executeUpdate(query);
        connection.close();
    }
}
