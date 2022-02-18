package com.app.domain.room;

import com.app.exceptions.PersistenceToFileException;
import com.app.utils.Utils;

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
        Room newRoom = new Room(findNewIdForTheRoom(), roomNumber, bedTypes);
        this.rooms.add(newRoom);
        return newRoom;
    }

    void addExistingGuest(int id, int roomNumber, BedType[] bedTypes) {
        Room newRoom = new Room(id, roomNumber, bedTypes);
        this.rooms.add(newRoom);
    }

    List<Room> getAll() {
        return rooms;
    }


    void writeAllRoomsToFile() {
        String name = "rooms.csv";
        Path file = Paths.get(
                System.getProperty("user.home"),
                "reservation_system",
                name);
        StringBuilder sb = new StringBuilder();
        for (Room room : this.rooms) {
            sb.append(room.toCSV());
        }
        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if (!Files.isDirectory(reservation_system_dir)) {
                Files.createDirectory(reservation_system_dir);
            }
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void readAllRoomsFromFile() {
        String filename = "rooms.csv";
        Path filepath = Paths.get(Utils.DATA_DIRECTORY.toString(), filename);

        if (!Files.exists(filepath)) {
            return;
        }

        try {
            String data = Files.readString(filepath, StandardCharsets.UTF_8);
            String[] roomsAsString = data.split(System.getProperty("line.separator"));
            for (String roomAsString : roomsAsString) {
                String[] roomData = roomAsString.split(",");
                if(roomData[0]==null || roomData[0].trim().isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(roomData[0]);
                int roomNumber = Integer.parseInt(roomData[1]);
                String bedTypesData = roomData[2];
                String[] bedTypesAsString = bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedTypesAsString.length];
                for (int i = 0; i < bedTypes.length; i++) {
                    bedTypes[i] = BedType.valueOf(bedTypesAsString[i]);
                }
                addExistingGuest(id, roomNumber, bedTypes);
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "write", "room data");
        }
    }

    private int findNewIdForTheRoom() {
        int max = 0;
        for (Room room : rooms) {
            if (room.id() > max) {
                max = room.id();
            }
        }
        return max + 1;
    }

    public void remove(int guestId) {
        int roomToBeRemoved = -1;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).id() == guestId) {
                roomToBeRemoved = i;
                break;
            }
        }
        if (roomToBeRemoved > -1) {
            rooms.remove(roomToBeRemoved);
        }
    }

    public void edit(int roomId, int numberRoom, BedType[] bedTypes) {
        remove(roomId);
        addExistingGuest(roomId, numberRoom, bedTypes);
    }

    public Room getById(int roomId) {
        for (Room room : rooms) {
            if (room.id() == roomId) {
                return room;
            }
        }
        return null;
    }
}
