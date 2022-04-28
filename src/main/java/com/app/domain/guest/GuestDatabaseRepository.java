package com.app.domain.guest;

import com.app.utils.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDatabaseRepository implements GuestRepository {

    private final static GuestRepository INSTANCE = new GuestDatabaseRepository();

    private final List<Guest> guests = new ArrayList<>();

    public static GuestRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String createGuestTemplate = "INSERT INTO GUESTS(FIRST_NAME, LAST_NAME, AGE, GENDER) VALUES('%s','%s',%d,'%s')";
            String createGuestQuery = String.format(createGuestTemplate, firstName, lastName, age, gender.toString());
            statement.execute(createGuestQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            long newId = -1;
            while (rs.next()) {
                newId = rs.getLong(1);
            }
            statement.close();

            Guest newGuest = new Guest(newId, firstName, lastName, age, gender);
            this.guests.add(newGuest);
            return newGuest;
        } catch (SQLException e) {
            System.out.println("Error when adding a room.");
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    public void writeAllGuestsToFile() {

    }

    @Override
    public void readAllGuestsFromFile() {
        try {
                Statement statement = SystemUtils.connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM GUESTS");

                while (rs.next()) {
                    long id = rs.getLong(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    int age = rs.getInt(4);

                    String genderAsString = rs.getString(5);

                    Gender gender = Gender.FEMALE;

                    if (SystemUtils.MALE.equals(genderAsString)) {
                        gender = Gender.MALE;
                    }

                    Guest newGuest = new Guest(id, firstName, lastName, age, gender);
                    this.guests.add(newGuest);
                }

                statement.close();

        } catch (SQLException e) {
            System.out.println("Error loading data.");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(long guestId) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeGuestTemplate = "DELETE FROM GUESTS WHERE ID=%d";
            String removeGuestQuery = String.format(removeGuestTemplate, guestId);
            statement.execute(removeGuestQuery);
            statement.close();
            this.removeById(guestId);
        } catch (SQLException e) {
            System.out.println("Error loading data.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(long guestId, String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String updateTemplate = "UPDATE GUESTS SET FIRST_NAME='%s', LAST_NAME='%s', AGE=%d, GENDER='%s' WHERE ID=%d";
            String updateQuery = String.format(updateTemplate, firstName, lastName, age, gender.toString(), guestId);
            statement.executeUpdate(updateQuery);
            statement.close();

            this.removeById(guestId);
            this.guests.add(new Guest(guestId, firstName, lastName, age, gender));
        } catch (SQLException e) {
            System.out.println("Error when editing a guest.");
            throw new RuntimeException(e);
        }
    }

    public void removeById(long guestId) {
        int indexToBeRemoved = -1;

        for (int i = 0; i < this.guests.size(); i++) {
            if (this.guests.get(i).getId() == guestId) {
                indexToBeRemoved = i;
                break;

            }
        }
        if (indexToBeRemoved != -1) {
            this.guests.remove(indexToBeRemoved);
        }
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
