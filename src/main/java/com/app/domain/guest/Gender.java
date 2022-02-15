package com.app.domain.guest;

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


}
