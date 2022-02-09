package com.app.domain.guest;

public class GuestService {

    private final GuestRepository guestRepository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = Gender.chooseFromNumberValue(genderOption);
        return guestRepository.createNewGuest(firstName, lastName, age, gender);
    }


}
