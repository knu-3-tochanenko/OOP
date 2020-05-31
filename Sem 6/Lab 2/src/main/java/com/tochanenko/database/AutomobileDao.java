package com.tochanenko.database;

import com.tochanenko.entities.AutoClass;
import com.tochanenko.entities.Automobile;

import java.sql.*;

public class AutomobileDao {
    public Automobile getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Automobiles WHERE id=" + id;

        ResultSet resultSet = statement.executeQuery(query);

        Automobile automobile = null;

        if (resultSet.next()) {
            int seats = resultSet.getInt("seats");
            String name = resultSet.getString("name");
            Date lastInspectionDate = resultSet.getDate("last_inspection_date");
            AutoClass autoClass = AutoClass.valueOf(resultSet.getString("class"));
            automobile = new Automobile(id, name, seats, lastInspectionDate, autoClass);
        }

        resultSet.close();
        connection.close();

        return automobile;
    }

    public void insert(Automobile automobile) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Automobiles (id, name, seats, last_inspection_date, class) VALUES(DEFAULT, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, automobile.getName());
        statement.setInt(2, automobile.getSeats());
        statement.setObject(3, automobile.getLastInspectionDate());
        statement.setString(4, automobile.getAutoClass().toString());

        statement.executeUpdate();
        connection.close();
    }
}
