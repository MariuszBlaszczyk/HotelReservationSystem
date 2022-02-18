package com.app.utils;

import com.app.exceptions.PersistenceToFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static final String HOTEL_NAME = "Overlook Hotel";
    public static final int SYSTEM_VERSION = 1;
    public static final boolean IS_DEVELOPER_VERSION = true;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 00;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 00;


    public static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.home"),
            "reservation_system");


    public static void createDataDirectory() {
        if (!Files.isDirectory(DATA_DIRECTORY)) {
            try {
                Files.createDirectory(DATA_DIRECTORY);
            } catch (IOException e) {
                throw new PersistenceToFileException(DATA_DIRECTORY.toString(), "create", "directory");
            }
        }
    }

}
