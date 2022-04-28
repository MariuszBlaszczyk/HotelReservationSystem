package com.app.domain.guest;


import com.app.domain.guest.dto.GuestDTO;

import java.util.Objects;

public final class Guest {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Gender gender;

    public Guest(long id, String firstName, String lastName, int age, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    String toCSV() {
        return String.format("%d,%s,%s,%d,%s%s", this.id, this.firstName, this.lastName, this.age, this.gender,
                System.getProperty("line.separator"));
    }


    public GuestDTO getAsDTO() {
        String gender = "MALE";
        if (this.gender.equals(Gender.FEMALE)) {
            gender = "FEMALE";
        }
        return new GuestDTO(this.id, this.firstName, this.lastName, this.age, gender);
    }


    @Override
    public String toString() {
        return id + " " +
                firstName + " " +
                lastName +
                ", age: " + age +
                ", gender: " + gender;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Guest) obj;
        return this.id == that.id &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                this.age == that.age &&
                Objects.equals(this.gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, gender);
    }


}
