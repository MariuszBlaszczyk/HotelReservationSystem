package com.app.domain.room;

public enum BedType {
    SINGLE(1, 1),
    DOUBLE(2, 2),
    KINGSIZE(3, 4);

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
