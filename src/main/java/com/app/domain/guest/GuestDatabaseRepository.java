package com.app.domain.guest;

import com.app.utils.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDatabaseRepository implements GuestRepository {

    private final static GuestRepository INSTANCE = new GuestDatabaseRepository();

    private List<Guest> guests = new ArrayList<>();

    public static GuestRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();

            String createGuestTemplate = "INSERT INTO GUESTS(FIRST_NAME. LAST_NAME, AGE, GENDER) VALUES ('%s', '%s', %d, 's')";
            String createGuestQuery = String.format(createGuestTemplate, firstName, lastName, age, gender.toString());
            statement.execute(createGuestQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet guestResultSet = statement.getGeneratedKeys();
            long guestNewId = -1;
            while (guestResultSet.next()) {
                guestNewId = guestResultSet.getLong(1);
            }
            statement.close();

            Guest newGuest = new Guest(guestNewId, firstName, lastName, age, gender);
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
            ResultSet guestResultSet = statement.executeQuery("SELECT * FROM GUESTS");

            while (guestResultSet.next()) {
                long id = guestResultSet.getLong(1);
                String firstName = guestResultSet.getString(2);
                String lastName = guestResultSet.getString(3);
                int age = guestResultSet.getInt(4);
                String genderAsString = guestResultSet.getString(5);

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
            statement.execute(updateQuery);
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
            if (this.guests.get(i).id() == guestId) {
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
            if (guest.id() == guestId) {
                return guest;
            }
        }
        return null;
    }
}
