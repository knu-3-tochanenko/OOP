package com.tochanenko;

import com.tochanenko.dao.TriangleDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Find triangle with area as close as possible to: ");
            float area = scanner.nextFloat();
            TriangleDao.getClosestByArea(area);

            System.out.print("Find triangles that have sum of areas as close as possible to: ");
            area = scanner.nextFloat();
            TriangleDao.getSumOfAreas(area);

            System.out.print("Find circles that csn fit inside circle with radius of: ");
            float r = scanner.nextFloat();
            TriangleDao.getInsideCircle(r);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
