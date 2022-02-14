package com.app.domain.room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    Room createNewRoom(int roomNumber, BedType[] bedTypes) {
        Room newRoom = new Room(roomNumber, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    List<Room> getAll() {
        return rooms;
    }
}
