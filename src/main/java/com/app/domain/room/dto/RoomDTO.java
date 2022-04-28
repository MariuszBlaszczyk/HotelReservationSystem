package com.app.domain.room.dto;

import java.util.Objects;

public final class RoomDTO {
    private final long id;
    private final int number;
    private final String beds;
    private final int bedsCount;
    private final int roomSize;

    public RoomDTO(long id, int number, String beds, int bedsCount, int roomSize) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.bedsCount = bedsCount;
        this.roomSize = roomSize;
    }

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

    public long id() {
        return id;
    }

    public int number() {
        return number;
    }

    public String beds() {
        return beds;
    }

    public int bedsCount() {
        return bedsCount;
    }

    public int roomSize() {
        return roomSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RoomDTO) obj;
        return this.id == that.id &&
                this.number == that.number &&
                Objects.equals(this.beds, that.beds) &&
                this.bedsCount == that.bedsCount &&
                this.roomSize == that.roomSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, beds, bedsCount, roomSize);
    }

    @Override
    public String toString() {
        return "RoomDTO[" +
                "id=" + id + ", " +
                "number=" + number + ", " +
                "beds=" + beds + ", " +
                "bedsCount=" + bedsCount + ", " +
                "roomSize=" + roomSize + ']';
    }

}
