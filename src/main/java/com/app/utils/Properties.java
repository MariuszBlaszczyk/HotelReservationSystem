package com.app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Properties {

    public static final String HOTEL_NAME = "Overlook Hotel";
    public static final int SYSTEM_VERSION = 1;
    public static final boolean IS_DEVELOPER_VERSION = true;

    public static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.home"),
            "reservation_system");


    public static void createDataDirectory() {
        if (!Files.isDirectory(DATA_DIRECTORY)) {
            try {
                Files.createDirectory(DATA_DIRECTORY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
