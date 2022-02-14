package com.app.domain.guest;

import com.app.exceptions.WrongOptionException;

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


    static Gender chooseFromNumberValue(int userValue) {
        Gender[] values = values();
        for (Gender gender : values) {
            if (gender.getValue() == userValue) {
                return gender;
            }
        }
        throw new WrongOptionException("Wrong option when selecting gender");
    }
}
