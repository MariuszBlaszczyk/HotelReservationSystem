package com.app.domain.room;

import com.app.domain.ObjectPool;
import com.app.domain.room.dto.RoomDTO;
import com.app.ui.text.TextUI;

import java.util.ArrayList;
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
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = TextUI.chooseBedTypeFromNumberValue(bedTypesOptions[i]);
        }
        return roomRepository.createNewRoom(roomNumber, bedTypes);
    }

    //nadpisujemy metodę do tworzenia pokoju, żeby zamiast tablicy int, która jest mało czytelna
    //wprowadzić listę Stringów, która ułatwi nam podawanie typów łóżek
    public Room createNewRoom(int roomNumber, List<String> bedTypeAsString) {
        BedType[] bedTypes = new BedType[bedTypeAsString.size()];//w Room mamy tablicę bedtype, więc tutaj też musimy
        //podać taką zmienną a jej wielkość to rozmiar listy podanej w argumencie

        for (int i = 0; i < bedTypeAsString.size(); i++) {

            bedTypes[i] = TextUI.chooseBedTypeFromEnum(bedTypeAsString.toString());

        }

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


    public void editGuestFromList(int roomId, int numberRoom, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = TextUI.chooseBedTypeFromNumberValue(bedTypesOptions[i]);
        }
        roomRepository.edit(roomId, numberRoom, bedTypes);
    }

    public void removeRoomFromList(int roomId) {
        roomRepository.remove(roomId);
    }


    public Room getRoomById(int roomId) {
        return roomRepository.getById(roomId);
    }


    public List<RoomDTO> getAllAsDTO() {
        List<RoomDTO> result = new ArrayList<>();
        List<Room> allRooms = roomRepository.getAll();

        for (Room room : allRooms) {
            RoomDTO dto = room.gemerateDTO();
            result.add(dto);
        }

        return result;
    }

}
