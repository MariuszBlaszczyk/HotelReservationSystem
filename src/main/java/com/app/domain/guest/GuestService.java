package com.app.domain.guest;

import com.app.ui.text.TextUI;

import java.util.List;

public class GuestService {

    private final static GuestRepository GUEST_REPOSITORY = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = TextUI.chooseGenderFromNumberValue(genderOption);
        return GUEST_REPOSITORY.createNewGuest(firstName, lastName, age, gender);
    }


    public List<Guest> getAllGuests() {
        return GUEST_REPOSITORY.getAll();
    }

    public void writeAllGuestsToFile() {
        GUEST_REPOSITORY.writeAllGuestsToFile();
    }

    public void readAllGuestsFromFile() {
        GUEST_REPOSITORY.readAllGuestsFromFile();
    }

    public void removeGuestFromList(int guestId) {
        GUEST_REPOSITORY.remove(guestId);
    }

    public void editGuestFromList(int guestId, String firstName, String lastName, int age, int genderOption) {
        Gender gender = TextUI.chooseGenderFromNumberValue(genderOption);
        GUEST_REPOSITORY.edit(guestId,firstName,lastName,age, gender);
    }

    public Guest getGuestById(int guestId) {
        return GUEST_REPOSITORY.getById(guestId);
    }
}
