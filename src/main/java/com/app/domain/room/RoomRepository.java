package com.app.domain.room;

public class RoomRepository {

    public Room createNewRoom(int roomNumber, BedType[] bedTypes) {
        return new Room(roomNumber, bedTypes);
    }
}
