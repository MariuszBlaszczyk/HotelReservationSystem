package com.app.domain.guest;

import java.util.List;

public class GuestService {

    private final GuestRepository guestRepository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = Gender.chooseFromNumberValue(genderOption);
        return guestRepository.createNewGuest(firstName, lastName, age, gender);
    }


    public List<Guest> getAllGuests() {
        return guestRepository.getAll();
    }

    public void saveAllGuestsToFile() {
        guestRepository.saveAllGuestsToFile();
    }

    public void readAllGuestsFromFile() {
        guestRepository.readAllGuestsFromFile();
    }
}
