package com.app.domain.room;

import com.app.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class Room {


    private final long id;
    private int number;
    private static List<BedType> beds = new ArrayList<>();

    Room(long id, int number, List<BedType> bedTypes) {
        this.id = id;
        this.number = number;
        beds = bedTypes;
    }

    String toCSV() {
        List<String> bedsAsString = getBedsAsString();
        String typesOfBeds = String.join("#", bedsAsString);
        return String.format("%d,%d,%s%s", this.id, this.number, typesOfBeds, System.getProperty("line.separator"));
    }

    private List<String> getBedsAsString() {
        List<String> bedsAsString = new ArrayList<>();
        for (BedType type : beds) {
            bedsAsString.add(type.toString());
        }
        return bedsAsString;
    }

    public RoomDTO generateDTO() {
        List<String> bedsAsString = getBedsAsString();
        String typesOfBeds = String.join(",", bedsAsString);
        int roomSize = 0;
        for (BedType bedType : beds) {
            roomSize += bedType.getSize();
        }
        return new RoomDTO(this.id, this.number, typesOfBeds, beds.size(), roomSize);
    }


    @Override
    public String toString() {
        return "ROOM ID " + id + "\n" +
                "- room number: " + number + "\n" +
                "- number of beds: " + beds.size() + "\n" +
                "- type of bed: " + beds + "\n";
    }


    void addBed(BedType bedType) {
        beds.add(bedType);
    }

    void setNumber(int numberRoom) {
        this.number = numberRoom;
    }

    public long id() {
        return id;
    }

    public int number() {
        return number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && number == room.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    public void setBeds(List<BedType> bedTypes) {
        beds = bedTypes;
    }
}
