package com.tochanenko.dao;

import com.tochanenko.db.DBConnection;
import com.tochanenko.db.ScriptLoader;
import com.tochanenko.util.SubList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        List<Float> inputs = new ArrayList<>();

        while (resultSet.next()) {
            inputs.add(resultSet.getFloat("area"));
        }


        SubList opt = new SubList();

        Set<SubList> sums = new HashSet<>();
        sums.add(opt);

        for (Float input : inputs) {
            Set<SubList> newSums = new HashSet<>();

            for (SubList sum : sums) {
                List<Float> newSubList = new ArrayList<>(sum.subList);
                newSubList.add(input);
                SubList newSum = new SubList(sum.size + input, newSubList);

                if (newSum.size <= area) {
                    newSums.add(newSum);

                    if (newSum.size > opt.size) {
                        opt = newSum;
                    }
                }
            }

            sums.addAll(newSums);
        }

        System.out.println("Closest values are: ");
        System.out.print(opt.subList.get(0));
        for (int i = 1; i < opt.subList.size(); i++) {
            System.out.print(" + " + opt.subList.get(i));
        }

        System.out.println();

    }

    public static void getInsideCircle(float r) throws SQLException, ClassNotFoundException {
        String query = ScriptLoader.load("query3.sql");

        Connection connection = DBConnection.initDB();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setFloat(1, r);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Triangle \u001B[96m#" + resultSet.getInt("id") + "\u001B[0m can fit inside.");
        }
    }
}
