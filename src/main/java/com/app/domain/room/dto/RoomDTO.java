package com.app.domain.room.dto;

public record RoomDTO(long id, int number, String beds, int bedsCount, int roomSize) {

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getBeds() {
        return beds;
    }

    public int getBedsCount() {
        return bedsCount;
    }


    public int getRoomSize() {
        return roomSize;
    }
}
