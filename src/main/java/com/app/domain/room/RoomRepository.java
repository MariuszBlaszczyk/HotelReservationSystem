package com.app.domain.room;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    Room createNewRoom(int roomNumber, BedType[] bedTypes) {
        Room newRoom = new Room(roomNumber, bedTypes);
        this.rooms.add(newRoom);
        return newRoom;
    }

    List<Room> getAll() {
        return rooms;
    }

    void saveAllRoomsToFile() {
        String filename = "rooms.csv";
        Path filepath = Paths.get(System.getProperty("user.home"), "reservation_system", filename);
        StringBuilder sb = new StringBuilder();
        for (Room room : this.rooms) {
            sb.append(room.toCSV());
        }
        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if (!Files.isDirectory(reservation_system_dir)) {
                Files.createDirectory(reservation_system_dir);
            }
            Files.writeString(filepath, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void readAllRoomsFromFile() {
        String filename = "rooms.csv";
        Path filepath = Paths.get(System.getProperty("user.home"), "reservation_system", filename);
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
            System.out.println("Failed to read the data file.");
            e.printStackTrace();
        }
    }
}
