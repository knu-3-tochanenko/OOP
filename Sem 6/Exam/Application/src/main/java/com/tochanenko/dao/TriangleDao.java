package com.tochanenko.dao;

import com.tochanenko.db.DBConnection;
import com.tochanenko.db.ScriptLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriangleDao {
    public static void getClosestByArea(float area) throws SQLException, ClassNotFoundException {
        String query = ScriptLoader.load("query1.sql");

        Connection connection = DBConnection.initDB();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setFloat(1, area);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Triangle with area as close to " + area + "is \u001B[96m#" + resultSet.getInt("id") + "\u001B[0m of difference \u001B[96m" + resultSet.getFloat("area") + "\u001B[0m");
        }
    }

    public static void getSumOfAreas(float area) throws SQLException, ClassNotFoundException {
        String query = ScriptLoader.load("query2.sql");

        Connection connection = DBConnection.initDB();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setFloat(1, area);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("WRITE RESULT HERE");
        }
    }

    public static void getInsideCircle(float r) throws SQLException, ClassNotFoundException {
        String query = ScriptLoader.load("query3.sql");

        Connection connection = DBConnection.initDB();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setFloat(1, r);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("WRITE RESULT HERE");
        }
    }
}
