package com.app.domain.guest;

public enum Gender {
    MALE(1),
    FEMALE(2);

    final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
