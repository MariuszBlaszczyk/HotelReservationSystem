package com.app.domain.room;

public enum BedType {
    SINGLE(1),
    DOUBLE(2),
    KING_SIZE(3);

    final int value;


    BedType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }





}
