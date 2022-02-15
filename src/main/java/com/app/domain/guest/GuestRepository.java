package com.app.domain.guest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private final List<Guest> guests = new ArrayList<>();

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }

    List<Guest> getAll() {
        return guests;
    }

    void saveAllGuestsToFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(System.getProperty("user.home"), "reservation_system", filename);
        StringBuilder sb = new StringBuilder();
        for (Guest guest : this.guests) {
            sb.append(guest.toCSV());
            try {
                Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
                if (!Files.isDirectory(reservation_system_dir)) {
                    Files.createDirectory(reservation_system_dir);
                }
                Files.writeString(filepath, sb.toString(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void readAllGuestsFromFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(System.getProperty("user.home"), "reservation_system", filename);
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
            System.out.println("Failed to read the data file.");
            e.printStackTrace();
        }
    }

}
