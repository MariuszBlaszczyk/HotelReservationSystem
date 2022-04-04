package com.app.domain.guest;

import com.app.utils.Utils;

public enum Gender {
    MALE(1, Utils.MALE),
    FEMALE(2, Utils.FEMALE);

    final int value;
    final String asString;

    Gender(int value, String asString) {
        this.value = value;
        this.asString = asString;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return asString;
    }
}
