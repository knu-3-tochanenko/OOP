package com.tochanenko.database;

import com.tochanenko.entities.User;
import com.tochanenko.entities.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public User getById(int id) throws SQLException, ClassNotFoundException {
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

    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "INSERT INTO Users (id, email, password, car_id, name, surname, role) ";
        String params = String.format(
                "VALUES(%d, '%s', '%s', %d, '%s', '%s', '%s')",
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getCarId(),
                user.getName(),
                user.getSurname(),
                user.getRole().toString()
        );

        statement.executeUpdate(query + params);
        connection.close();
    }
}
