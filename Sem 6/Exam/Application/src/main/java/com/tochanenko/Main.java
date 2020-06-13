package com.tochanenko;

import com.tochanenko.dao.TriangleDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Find triangle with area as close as possible to: ");
        float area = scanner.nextFloat();

        try {
            TriangleDao.getClosestByArea(area);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
