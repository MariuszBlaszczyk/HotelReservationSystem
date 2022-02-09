package com.app.domain.guest;

import java.util.Objects;

public record Guest(String firstName, String lastName, int age, Gender gender) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return age == guest.age && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && gender == guest.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, gender);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age: " + age + ", gender: " + gender + ".";
    }

}
