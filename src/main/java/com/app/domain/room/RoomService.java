package com.app.domain.room;

import com.app.domain.ObjectPool;
import com.app.domain.room.dto.RoomDTO;
import com.app.exceptions.WrongOptionException;
import com.app.ui.text.TextUI;
import com.app.utils.SystemUtils;

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


    private List<BedType> getBedTypes(List<String> bedTypesAsString) {
        BedType[] bedTypes = new BedType[bedTypesAsString.size()];

        for (int i = 0; i < bedTypesAsString.size(); i = i + 1) {

            BedType bedType;

            if (bedTypesAsString.get(i).equals(SystemUtils.SINGLE_BED)) {
                bedType = BedType.SINGLE;
            } else if (bedTypesAsString.get(i).equals(SystemUtils.DOUBLE_BED)) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesAsString.get(i).equals(SystemUtils.KINGSIZE_BED)) {
                bedType = BedType.KINGSIZE;
            } else {
                throw new WrongOptionException("Wrong option when selecting bed type");
            }

            bedTypes[i] = bedType;
        }
        return Arrays.asList(bedTypes);
    }

    private List<BedType> getBedTypes(int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];

        for (int i = 0; i < bedTypesOptions.length; i = i + 1) {

            BedType bedType;

            if (bedTypesOptions[i] == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypesOptions[i] == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesOptions[i] == 3) {
                bedType = BedType.KINGSIZE;
            } else {
                throw new WrongOptionException("Wrong option when selecting bed type");
            }

            bedTypes[i] = bedType;
        }
        return Arrays.asList(bedTypes);
    }


    public List<Room> getAllRooms() {
        return this.roomRepository.getAll();
    }

    public void writeAllRoomsToFile() {
        this.roomRepository.writeAllRoomsToFile();
    }

    public void readAllRoomsFromFile() {
        this.roomRepository.readAllRoomsFromFile();
    }


    public void editRoomFromList(long roomId, int numberRoom, int[] bedTypesOptions) {
        List<BedType> bedTypes = getBedTypes(bedTypesOptions);
        this.roomRepository.edit(roomId, numberRoom, bedTypes);
    }



    public void editRoomFromList(long id, int number, List<String> bedTypeAsString) {
        List<BedType> bedTypes = getBedTypes(bedTypeAsString);
        this.roomRepository.edit(id, number, bedTypes);
    }



    public void removeRoomFromList(long roomId) {
       this.roomRepository.remove(roomId);
    }


    public Room getRoomById(long roomId) {
        return this.roomRepository.getById(roomId);
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
