package com.app.domain.room;

import com.app.ui.text.TextUI;

import java.util.List;

public class RoomService {

    private final static RoomRepository ROOM_REPOSITORY = new RoomRepository();

    public Room createNewRoom(int roomNumber, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = TextUI.chooseBedTypeFromNumberValue(bedTypesOptions[i]);
        }
        return ROOM_REPOSITORY.createNewRoom(roomNumber, bedTypes);
    }


    public List<Room> getAllRooms() {
        return ROOM_REPOSITORY.getAll();
    }

    public void writeAllRoomsToFile() {
        ROOM_REPOSITORY.writeAllRoomsToFile();
    }

    public void readAllRoomsFromFile() {
        ROOM_REPOSITORY.readAllRoomsFromFile();
    }


    public void editGuestFromList(int roomId, int numberRoom, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = TextUI.chooseBedTypeFromNumberValue(bedTypesOptions[i]);
        }
        ROOM_REPOSITORY.edit(roomId, numberRoom, bedTypes);
    }

    public void removeRoomFromList(int roomId) {
        ROOM_REPOSITORY.remove(roomId);
    }


    public Room getRoomById(int roomId) {
        return ROOM_REPOSITORY.getById(roomId);
    }
}
