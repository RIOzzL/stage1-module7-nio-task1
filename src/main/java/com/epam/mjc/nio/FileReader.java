package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file));) {
            List<String> list = Files.readAllLines(file.toPath());
            for (String stringOfFile : list) {
                String[] split = stringOfFile.split("[:|\\n]");
                map.put(split[0].trim(), split[1].trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Profile(map.get("Name"), Integer.parseInt(map.get("Age")), map.get("Email"), Long.parseLong(map.get("Phone")));
    }
}
