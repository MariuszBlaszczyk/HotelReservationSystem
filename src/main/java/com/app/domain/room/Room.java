package com.app.domain.room;

import com.app.domain.room.dto.RoomDTO;

import java.util.Arrays;


public record Room(int id, int number, BedType[] bedType) {


    String toCSV() {
        String[] bedsAsString = getBedsAsString();
        String typesOfBeds = String.join("#", bedsAsString);
        return String.format("%d,%d,%s%s", this.id, this.number, typesOfBeds, System.getProperty("line.separator"));
    }

    private String[] getBedsAsString() {
        String[] bedsAsString = new String[this.bedType.length];
        for (int i = 0; i < this.bedType.length; i++) {
            bedsAsString[i] = this.bedType[i].toString();
        }
        return bedsAsString;
    }

    public RoomDTO gemerateDTO() {
        String[] bedsAsString = getBedsAsString();
        String typesOfBeds = String.join(",", bedsAsString);
        int roomSize = 0;
        for (BedType bedType : bedType) {
            roomSize += bedType.getSize();
        }
        return new RoomDTO(this.id, this.number, typesOfBeds, bedType.length, roomSize);
    }


    @Override
    public String toString() {
        return "ROOM ID " + id + "\n" +
                "- room number: " + number + "\n" +
                "- number of beds: " + bedType.length + "\n" +
                "- type of bed: " + Arrays.toString(bedType) + "\n";
    }


}
