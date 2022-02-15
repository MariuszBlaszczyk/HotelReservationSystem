package com.app.domain.guest;

import com.app.exceptions.WrongOptionException;

public enum Gender {
    MALE(1),
    FEMALE(2);

    final int VALUE;

    Gender(int VALUE) {
        this.VALUE = VALUE;
    }

    public int getVALUE() {
        return VALUE;
    }


    static Gender chooseFromNumberValue(int userValue) {
        Gender[] values = values();
        for (Gender gender : values) {
            if (gender.getVALUE() == userValue) {
                return gender;
            }
        }
        throw new WrongOptionException("Wrong option when selecting gender");
    }
}
