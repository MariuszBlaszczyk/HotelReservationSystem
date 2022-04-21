package com.app.domain.room;

import com.app.domain.ObjectPool;
import com.app.domain.room.dto.RoomDTO;
import com.app.ui.text.TextUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository = ObjectPool.getRoomRepository();

    private final static RoomService INSTANCE = new RoomService();

    private RoomService() {
    }

    public static RoomService getInstance() {
        return INSTANCE;
    }

    public Room createNewRoom(int roomNumber, int[] bedTypesOptions) {
        List<BedType> bedTypes = getBedTypes(bedTypesOptions);
        return roomRepository.createNewRoom(roomNumber, bedTypes);
    }


    public Room createNewRoom(int roomNumber, List<String> bedTypeAsString) {
        List<BedType> bedTypes = getBedTypes(bedTypeAsString);
        return roomRepository.createNewRoom(roomNumber, bedTypes);
    }


    public List<Room> getAllRooms() {
        return roomRepository.getAll();
    }

    public void writeAllRoomsToFile() {
        roomRepository.writeAllRoomsToFile();
    }

    public void readAllRoomsFromFile() {
        roomRepository.readAllRoomsFromFile();
    }


    public void editRoomFromList(long roomId, int numberRoom, int[] bedTypesOptions) {
        List<BedType> bedTypes = getBedTypes(bedTypesOptions);
        roomRepository.edit(roomId, numberRoom, bedTypes);
    }

    private List<BedType> getBedTypes(int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = TextUI.chooseBedTypeFromNumberValue(bedTypesOptions[i]);
        }
        return Arrays.asList(bedTypes);
    }

    public void editRoomFromList(long id, int number, List<String> bedTypeAsString) {
        List<BedType> bedTypes = getBedTypes(bedTypeAsString);
        this.roomRepository.edit(id, number, bedTypes);
    }

    private List<BedType> getBedTypes(List<String> bedTypeAsString) {
        BedType[] bedTypes = new BedType[bedTypeAsString.size()];
        for (int i = 0; i < bedTypeAsString.size(); i++) {
            bedTypes[i] = TextUI.chooseBedTypeFromEnum(bedTypeAsString.get(i));
        }
        return Arrays.asList(bedTypes);
    }

    public void removeRoomFromList(long roomId) {
        roomRepository.remove(roomId);
    }


    public Room getRoomById(long roomId) {
        return roomRepository.getById(roomId);
    }


    public List<RoomDTO> getAllAsDTO() {
        List<RoomDTO> result = new ArrayList<>();
        List<Room> allRooms = roomRepository.getAll();

        for (Room room : allRooms) {
            RoomDTO dto = room.generateDTO();
            result.add(dto);
        }

        return result;
    }

}
