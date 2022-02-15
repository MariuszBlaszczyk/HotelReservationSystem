package com.app.domain.room;

import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository = new RoomRepository();

    public Room createNewRoom(int roomNumber, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = BedType.chooseFromNumberValue(bedTypesOptions[i]);
        }
        return this.roomRepository.createNewRoom(roomNumber, bedTypes);
    }

    public List<Room> getAllRooms() {
        return this.roomRepository.getAll();
    }

    public void saveAllRoomsToFile() {
        this.roomRepository.saveAllRoomsToFile();
    }

    public void readAllRoomsFromFile() {
        this.roomRepository.readAllRoomsFromFile();
    }


}
