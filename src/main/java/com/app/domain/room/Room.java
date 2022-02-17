package com.app.domain.room;

import java.util.Arrays;


public record Room(int id, int number, BedType[] bedType) {


    String toCSV() {
        String[] bedsAsString = new String[this.bedType.length];
        for (int i = 0; i < this.bedType.length; i++) {
            bedsAsString[i] = this.bedType[i].toString();
        }
        String typesOfBeds = String.join("#", bedsAsString);
        return String.format("%d,%d,%s%s", this.id, this.number, typesOfBeds, System.getProperty("line.separator"));
    }


    @Override
    public String toString() {
        return "ROOM ID " + id + "\n" +
                "- room number: " + number + "\n" +
                "- number of beds: " + bedType.length + "\n" +
                "- type of bed: " + Arrays.toString(bedType) + ".\n";
    }
}
