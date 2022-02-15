package com.app.domain.room;

public enum BedType {
    SINGLE(1),
    DOUBLE(2),
    KING_SIZE(3);

    final int VALUE;


    BedType(int value) {
        this.VALUE = value;
    }

    public int getVALUE() {
        return VALUE;
    }





}
