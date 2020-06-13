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
            System.out.println("#" + resultSet.getInt("id") + " of area " + resultSet.getFloat("area"));
        }
    }
}
