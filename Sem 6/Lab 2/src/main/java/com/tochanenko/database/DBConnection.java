package com.tochanenko.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection initDB() throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:postgresql://licalhost:5432/motor_depot";
        String dbUsername = "postgres";
        String dbPassword = "root";

        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}