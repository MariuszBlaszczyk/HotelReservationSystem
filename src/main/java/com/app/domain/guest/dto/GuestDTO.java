package com.app.domain.guest.dto;

public record GuestDTO(long id, String firstName, String lastName, int age,
                       String gender) {

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }


}
