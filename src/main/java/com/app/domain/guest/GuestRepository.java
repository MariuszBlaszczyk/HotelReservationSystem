package com.app.domain.guest;

import com.app.exceptions.PersistenceToFileException;
import com.app.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private final static GuestRepository INSTANCE = new GuestRepository();

    private final List<Guest> guests = new ArrayList<>();

    private GuestRepository() {
    }

    public static GuestRepository getInstance() {
        return INSTANCE;
    }

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(findNewIdForTheGuest(), firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }

    void addExistingGuest(int id, String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(id, firstName, lastName, age, gender);
        guests.add(newGuest);
    }


    List<Guest> getAll() {
        return guests;
    }

    void writeAllGuestsToFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(Utils.DATA_DIRECTORY.toString(), filename);
        StringBuilder sb = new StringBuilder();
        for (Guest guest : this.guests) {
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
        Path filepath = Paths.get(Utils.DATA_DIRECTORY.toString(), filename);

        if (!Files.exists(filepath)) {
            return;
        }
        try {
            String data = Files.readString(filepath, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));
            for (String guestAsString : guestsAsString) {
                String[] guestData = guestAsString.split(",");
                if(guestData[0]==null || guestData[0].trim().isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(guestData[0]);
                int age = Integer.parseInt(guestData[3]);
                Gender gender = Gender.valueOf(guestData[4]);
                addExistingGuest(id, guestData[1], guestData[2], age, gender);
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "read", "guest data");
        }
    }

    private int findNewIdForTheGuest() {
        int max = 0;
        for (Guest guest : this.guests) {
            if (guest.id() > max) {
                max = guest.id();
            }
        }
        return max + 1;
    }

    public void remove(int guestId) {
        int guestToBeRemoved = -1;
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).id() == guestId) {
                guestToBeRemoved = i;
                break;
            }
        }
        if (guestToBeRemoved > -1) {
            guests.remove(guestToBeRemoved);
        }
    }

    public void edit(int guestId, String firstName, String lastName, int age, Gender gender) {
        remove(guestId);
        addExistingGuest(guestId, firstName, lastName, age, gender);
    }

    public Guest getById(int guestId) {
        for (Guest guest : guests) {
            if (guest.id() == guestId) {
                return guest;
            }
        }
        return null;
    }
}
