package com.app.domain.room;

import com.app.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class Room {


    private final long id;
    private int number;
    private List<BedType> beds = new ArrayList<>();

    Room(long id, int number, List<BedType> bedTypes) {
        this.id = id;
        this.number = number;
        beds = bedTypes;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    void setNumber(int numberRoom) {
        this.number = numberRoom;
    }

    public void setBeds(List<BedType> bedTypes) {
        beds = bedTypes;
    }

    public String getInfo() {

        StringBuilder bedInfo = new StringBuilder("Types of beds in the room:\n");
        for (BedType bed : beds) {
            bedInfo.append("\t").append(bed).append("\n");
        }

        return String.format("%d Numer: %d %s", this.id, this.number, bedInfo);
    }

    String toCSV() {

        List<String> bedsAsString = getBedsAsStrings();

        String bedTypes = String.join("#", bedsAsString);

        return String.format("%d,%d,%s%s", this.id, this.number, bedTypes, System.getProperty("line.separator"));

    }

    private List<String> getBedsAsStrings() {

        List<String> bedsAsString = new ArrayList<>();

        for (BedType bed : this.beds) {
            bedsAsString.add(bed.toString());
        }
        return bedsAsString;
    }

    public RoomDTO generateDTO() {

        List<String> bedsAsString = getBedsAsStrings();

        String bedTypes = String.join(",", bedsAsString);

        int roomSize = 0;

        for (BedType bedType : beds) {
            roomSize += bedType.getSize();
        }

        return new RoomDTO(this.id, this.number, bedTypes, beds.size(), roomSize);
    }


    void addBed(BedType bedType) {
        this.beds.add(bedType);
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


    @Override
    public String toString() {
        return "ROOM ID " + id + "\n" +
                "- room number: " + number + "\n" +
                "- number of beds: " + beds.size() + "\n" +
                "- type of bed: " + beds + "\n";
    }
}
