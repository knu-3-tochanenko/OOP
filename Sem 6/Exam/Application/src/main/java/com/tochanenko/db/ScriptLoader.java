package com.tochanenko.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScriptLoader {

    public static String load(String fileName) {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        File file = new File("C:/Workspace/Repos/knu-3/OOP/OOP - Exam/Sem 6/Exam/Application/src/main/resources/" + fileName);
        StringBuilder res = new StringBuilder("");

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                res.append(line);
                res.append("\n");
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toString();
    }
}
