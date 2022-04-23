package com.app.domain.guest;

import java.util.List;

public interface GuestRepository {
    Guest createNewGuest(String firstName, String lastName, int age, Gender gender);

    List<Guest> getAll();

    void writeAllGuestsToFile();

    void readAllGuestsFromFile();

    void remove(long guestId);

    void edit(long guestId, String firstName, String lastName, int age, Gender gender);

    Guest getById(long guestId);
}
