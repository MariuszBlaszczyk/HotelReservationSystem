package com.app.domain.room;

import com.app.exceptions.WrongOptionException;

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


    static BedType chooseFromNumberValue(int userValue) {
        BedType[] values = values();
        for (BedType bedType : values) {
            if (bedType.getVALUE() == userValue)
                return bedType;
        }
        throw new WrongOptionException("Wrong option when selecting bed type");
    }


}
