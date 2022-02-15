package com.app.domain.room;

import java.util.List;

public class RoomService {

    private final RoomRepository ROOM_REPOSITORY = new RoomRepository();

    public Room createNewRoom(int roomNumber, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = BedType.chooseFromNumberValue(bedTypesOptions[i]);
        }
        return this.ROOM_REPOSITORY.createNewRoom(roomNumber, bedTypes);
    }

    public List<Room> getAllRooms() {
        return this.ROOM_REPOSITORY.getAll();
    }

    public void saveAllRoomsToFile() {
        this.ROOM_REPOSITORY.saveAllRoomsToFile();
    }

    public void readAllRoomsFromFile() {
        this.ROOM_REPOSITORY.readAllRoomsFromFile();
    }


}
