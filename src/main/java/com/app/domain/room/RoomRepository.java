package com.app.domain.room;

import com.app.exceptions.PersistenceToFileException;
import com.app.utils.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final List<Room> ROOMS = new ArrayList<>();

    Room createNewRoom(int roomNumber, BedType[] bedTypes) {
        Room newRoom = new Room(roomNumber, bedTypes);
        this.ROOMS.add(newRoom);
        return newRoom;
    }

    List<Room> getAll() {
        return ROOMS;
    }

    void saveAllRoomsToFile() {
        String filename = "rooms.csv";
        Path filepath = Paths.get(Properties.DATA_DIRECTORY.toString(), filename);
        StringBuilder sb = new StringBuilder();
        for (Room room : this.ROOMS) {
            sb.append(room.toCSV());
        }
        try {
            Files.writeString(filepath, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "write", "room data");
        }
    }


    void readAllRoomsFromFile() {
        String filename = "rooms.csv";
        Path filepath = Paths.get(Properties.DATA_DIRECTORY.toString(), filename);
        try {
            String data = Files.readString(filepath, StandardCharsets.UTF_8);
            String[] roomsAsString = data.split(System.getProperty("line.separator"));
            for (String roomAsString : roomsAsString) {
                String[] roomData = roomAsString.split(",");
                int roomNumber = Integer.parseInt(roomData[0]);
                String bedTypesData = roomData[1];
                String[] bedTypesAsString = bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedTypesAsString.length];
                for (int i = 0; i < bedTypes.length; i++) {
                    bedTypes[i] = BedType.valueOf(bedTypesAsString[i]);
                }
                createNewRoom(roomNumber, bedTypes);

            }
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "write", "room data");
        }
    }
}
