package com.app.domain.room;

public enum BedType {
    SINGLE(1, 2),
    DOUBLE(2, 2),
    KING_SIZE(3, 2);

    private final int value;
    private final int size;


    BedType(int value, int size) {
        this.value = value;
        this.size = size;
    }


    public int getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }
}
