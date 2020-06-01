package com.tochanenko.utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SqlFileLoader {
    public static String load(String fileName) {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        File file = new File("C:/Workspace/Repos/knu-3/OOP/OOP - Lab 2/Sem 6/Lab 2/src/main/resources/scripts/" + fileName);
        StringBuilder res = new StringBuilder("");

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                res.append(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toString();
    }
}
