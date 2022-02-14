package com.app.domain.room;

import com.app.exceptions.WrongOptionException;

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


    static BedType chooseFromNumberValue(int userValue) {
        BedType[] values = values();
        for (BedType bedType : values) {
            if (bedType.getValue() == userValue)
                return bedType;
        }
        throw new WrongOptionException("Wrong option when selecting bed type");
    }


}
