package com.app.utils;

import com.app.exceptions.PersistenceToFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class SystemUtils {

    public static final String HOTEL_NAME = "Overlook Hotel";
    public static String SYSTEM_VERSION;
    public static final boolean IS_DEVELOPER_VERSION = true;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 0;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 0;

    public static final String SINGLE_BED = "Single";
    public static final String DOUBLE_BED = "Double";
    public static final String KINGSIZE_BED = "Kingsize";


    public static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.home"),
            "reservation_system");

    public static final String FEMALE = "Female";
    public static final String MALE = "Male";

    public static Connection connection;


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


    public void createDatabaseConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/reservationSystem/ipinbarbot;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "test", "");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ROOMS(ID INT PRIMARY KEY AUTO_INCREMENT, ROOM_NUMBER INT NOT NULL UNIQUE)");
            statement.execute("CREATE TABLE IF NOT EXISTS BEDS(ID INT PRIMARY KEY AUTO_INCREMENT, ROOM_ID INT NOT NULL, BED VARCHAR2(55)," +
                    " FOREIGN KEY (ROOM_ID) REFERENCES ROOMS(ID))");
            statement.execute("CREATE TABLE IF NOT EXISTS GUESTS(ID INT PRIMARY KEY AUTO_INCREMENT, FIRST_NAME VARCHAR2(100) NOT NULL , " +
                    "LAST_NAME VARCHAR2(100) NOT NULL, AGE NUMBER NOT NULL, GENDER VARCHAR2(25) NOT NULL)");
            statement.execute("CREATE TABLE IF NOT EXISTS RESERVATIONS(ID INT PRIMARY KEY AUTO_INCREMENT, ROOM_ID INT NOT NULL, " +
                    "GUEST_ID INT NOT NULL, RES_FROM SMALLDATETIME NOT NULL, RES_TO SMALLDATETIME NOT NULL, FOREIGN KEY (ROOM_ID) REFERENCES ROOMS(ID), " +
                    "FOREIGN KEY (GUEST_ID) REFERENCES GUESTS(ID))");

            System.out.println("Successfully establishing a connection to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while creating connection to database" + e.getMessage());
            e.printStackTrace();
        }
    }
}
