package com.app.domain.guest;

import com.app.exceptions.PersistenceToFileException;
import com.app.utils.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private final List<Guest> GUESTS = new ArrayList<>();

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        GUESTS.add(newGuest);
        return newGuest;
    }

    List<Guest> getAll() {
        return GUESTS;
    }

    void saveAllGuestsToFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(Properties.DATA_DIRECTORY.toString(), filename);
        StringBuilder sb = new StringBuilder();
        for (Guest guest : this.GUESTS) {
            sb.append(guest.toCSV());
            try {
                Files.writeString(filepath, sb.toString(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new PersistenceToFileException(filepath.toString(), "write", "guest data");
            }
        }
    }

    void readAllGuestsFromFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(Properties.DATA_DIRECTORY.toString(), filename);
        try {
            String data = Files.readString(filepath, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));
            for (String guestAsString : guestsAsString) {
                String[] guestData = guestAsString.split(",");
                int age = Integer.parseInt(guestData[2]);
                Gender gender = Gender.valueOf(guestData[3]);
                createNewGuest(guestData[0], guestData[1], age, gender);
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "read", "guest data");
        }
    }

}
