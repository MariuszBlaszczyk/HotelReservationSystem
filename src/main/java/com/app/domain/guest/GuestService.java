package com.app.domain.guest;

import com.app.domain.ObjectPool;
import com.app.domain.guest.dto.GuestDTO;
import com.app.ui.text.TextUI;

import java.util.ArrayList;
import java.util.List;

public class GuestService {

    private final static GuestService INSTANCE = new GuestService();

    private final GuestRepository guestRepository = ObjectPool.getGuestRepository();

    private GuestService() {
    }

    public static GuestService getInstance() {
        return INSTANCE;
    }


    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = TextUI.chooseGenderFromNumberValue(genderOption);
        return guestRepository.createNewGuest(firstName, lastName, age, gender);
    }

    public Guest createNewGuest(String firstName, String lastName, int age, boolean isMale) {
        Gender gender = Gender.FEMALE;
        if (isMale) {
            gender = Gender.MALE;
        }
        return guestRepository.createNewGuest(firstName, lastName, age, gender);
    }


    public List<Guest> getAllGuests() {
        return this.guestRepository.getAll();
    }

    public void writeAllGuestsToFile() {
        this.guestRepository.writeAllGuestsToFile();
    }

    public void readAllGuestsFromFile() {
        this.guestRepository.readAllGuestsFromFile();
    }

    public void removeGuestFromList(long guestId) {
        this.guestRepository.remove(guestId);
    }

    public void editGuestFromList(long guestId, String firstName, String lastName, int age, int genderOption) {
        Gender gender = TextUI.chooseGenderFromNumberValue(genderOption);
       this.guestRepository.edit(guestId, firstName, lastName, age, gender);
    }

    public Guest getGuestById(long guestId) {
        return this.guestRepository.getById(guestId);
    }


    public List<GuestDTO> getGuestsAsDTO() {
        List<GuestDTO> result = new ArrayList<>();

        List<Guest> allGuests = guestRepository.getAll();

        for (Guest guest : allGuests) {
            GuestDTO guestDTO = guest.getAsDTO();
            result.add(guestDTO);
        }
        return result;
    }
}
