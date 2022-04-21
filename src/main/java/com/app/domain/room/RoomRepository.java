package com.app.domain.room;

import java.util.List;

public interface RoomRepository {


    void writeAllRoomsToFile();

    void readAllRoomsFromFile();

    Room createNewRoom(int roomNumber, List<BedType> bedTypes);

    List<Room> getAll();

    void remove(long roomId);

    void edit(long roomId, int numberRoom, List<BedType> bedTypes);

    Room getById(long roomId);
}
