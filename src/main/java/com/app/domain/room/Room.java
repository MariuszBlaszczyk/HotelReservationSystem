package com.app.domain.room;

import java.util.Arrays;
import java.util.Objects;

public record Room(int number, BedType[] bedType) {


    String toCSV() {
        String[] bedsAsString = new String[this.bedType.length];
        for (int i = 0; i < this.bedType.length; i++) {
            bedsAsString[i] = this.bedType[i].toString();
        }
        String typesOfBeds = String.join("#", bedsAsString);
        return String.format("%d,%s%s", this.number, typesOfBeds, System.getProperty("line.separator"));
    }

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
        return "ROOM\n- number: " + number + ".\n- number of beds: " + bedType.length +
                ".\n- type of bed: " + Arrays.toString(bedType) + ".\n";
    }
}
