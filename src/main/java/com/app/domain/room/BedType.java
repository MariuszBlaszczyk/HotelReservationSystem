package com.app.domain.room;

import com.app.utils.Utils;


public enum BedType {
    SINGLE(1, 1, Utils.SINGLE_BED),
    DOUBLE(2, 2, Utils.DOUBLE_BED),
    KINGSIZE(3, 4, Utils.KINGSIZE_BED);

    private final int value;
    private final int size;
    private final String asString;


    BedType(int value, int size, String asString) {
        this.value = value;
        this.size = size;
        this.asString = asString;
    }

    public int getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return this.asString;
    }
}

