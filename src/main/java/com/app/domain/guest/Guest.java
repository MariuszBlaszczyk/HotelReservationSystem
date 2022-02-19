package com.app.domain.guest;


public record Guest(int id, String firstName, String lastName, int age, Gender gender) {


    String toCSV() {
        return String.format("%d,%s,%s,%d,%s%s", this.id, this.firstName, this.lastName, this.age, this.gender,
                System.getProperty("line.separator"));
    }


    @Override
    public String toString() {
        return id + " " +
                firstName + " " +
                lastName +
                ", age: " + age +
                ", gender: " + gender;
    }

}
