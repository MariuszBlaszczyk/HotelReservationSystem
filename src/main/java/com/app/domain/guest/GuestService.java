package com.app.domain.guest;

import com.app.ui.TextUI;

import java.util.List;

public class GuestService {

    private final GuestRepository GUEST_REPOSITORY = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = TextUI.chooseGenderFromNumberValue(genderOption);
        return GUEST_REPOSITORY.createNewGuest(firstName, lastName, age, gender);
    }


    public List<Guest> getAllGuests() {
        return GUEST_REPOSITORY.getAll();
    }

    public void saveAllGuestsToFile() {
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
}
