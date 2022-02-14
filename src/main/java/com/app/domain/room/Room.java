package com.app.domain.room;

import java.util.Arrays;
import java.util.Objects;

public record Room(int number, BedType[] bedType) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number && Arrays.equals(bedType, room.bedType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(number);
        result = 31 * result + Arrays.hashCode(bedType);
        return result;
    }

    @Override
    public String toString() {
        return "- room number " + number + ".\n- number of beds: " + bedType.length +
                ".\n- type of bed: " + Arrays.toString(bedType) + ".";
    }
}
