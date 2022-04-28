package com.app.domain.guest;

import com.app.exceptions.PersistenceToFileException;
import com.app.utils.SystemUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository implements GuestRepository {

    private final static GuestRepository INSTANCE = new GuestFileRepository();

    private final List<Guest> guests = new ArrayList<>();

    private GuestFileRepository() {
    }

    public static GuestRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(findNewIdForTheGuest(), firstName, lastName, age, gender);
        this.guests.add(newGuest);
        return newGuest;
    }

    Guest addExistingGuest(long id, String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(id, firstName, lastName, age, gender);
        this.guests.add(newGuest);
        return newGuest;
    }


    @Override
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    public void writeAllGuestsToFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), filename);
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

    @Override
    public void readAllGuestsFromFile() {
        String filename = "guests.csv";
        Path filepath = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), filename);

        if (!Files.exists(filepath)) {
            return;
        }
        try {
            String data = Files.readString(filepath, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));
            for (String guestAsString : guestsAsString) {
                String[] guestData = guestAsString.split(",");
                if (guestData[0] == null || guestData[0].trim().isEmpty()) {
                    continue;
                }
                long id = Long.parseLong(guestData[0]);
                int age = Integer.parseInt(guestData[3]);
                Gender gender;
                if (guestData[4].equals(SystemUtils.MALE)) {
                    gender = Gender.MALE;
                } else {
                    gender = Gender.FEMALE;
                }


                addExistingGuest(id, guestData[1], guestData[2], age, gender);
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "read", "guest data");
        }
    }

    private long findNewIdForTheGuest() {
        long max = 0;
        for (Guest guest : this.guests) {
            if (guest.getId() > max) {
                max = guest.getId();
            }
        }
        return max + 1;
    }

    @Override
    public void remove(long guestId) {
        int guestToBeRemoved = -1;
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId() == guestId) {
                guestToBeRemoved = i;
                break;
            }
        }
        if (guestToBeRemoved > -1) {
            guests.remove(guestToBeRemoved);
        }
    }

    @Override
    public void edit(long guestId, String firstName, String lastName, int age, Gender gender) {
        this.remove(guestId);
        this.addExistingGuest(guestId, firstName, lastName, age, gender);
    }

    @Override
    public Guest getById(long guestId) {
        for (Guest guest : this.guests) {
            if (guest.getId() == guestId) {
                return guest;
            }
        }
        return null;
    }
}
