package com.app.utils;

import com.app.exceptions.PersistenceToFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class SystemUtils {

    public static final String HOTEL_NAME = "Overlook Hotel";
    public static String SYSTEM_VERSION;
    public static final boolean IS_DEVELOPER_VERSION = true;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 00;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 00;

    public static final String SINGLE_BED = "Single";
    public static final String DOUBLE_BED = "Double";
    public static final String KINGSIZE_BED = "Kingsize";


    public static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.home"),
            "reservation_system");

    public static final String FEMALE = "Female";
    public static final String MALE = "Male";

    public SystemUtils() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream(".properties"));
            SystemUtils.SYSTEM_VERSION = properties.get("system.version").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
