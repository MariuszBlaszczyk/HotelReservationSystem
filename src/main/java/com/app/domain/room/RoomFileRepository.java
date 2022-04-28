package com.app.domain.room;

import com.app.exceptions.PersistenceToFileException;
import com.app.utils.SystemUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomFileRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    private final static RoomRepository INSTANCE = new RoomFileRepository();

    private RoomFileRepository() {
    }

    public static RoomRepository getINSTANCE() {
        return INSTANCE;
    }

    public Room createNewRoom(int roomNumber, List<BedType> bedTypes) {
        Room newRoom = new Room(findNewIdForTheRoom(), roomNumber, bedTypes);
        this.rooms.add(newRoom);
        return newRoom;
    }


    void addExistingGuest(long id, int roomNumber, List<BedType> bedTypes) {
        Room newRoom = new Room(id, roomNumber, bedTypes);
        this.rooms.add(newRoom);
    }

    public List<Room> getAll() {
        return rooms;
    }


    @Override
    public void writeAllRoomsToFile() {
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


    @Override
    public void readAllRoomsFromFile() {
        String filename = "rooms.csv";
        Path filepath = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), filename);

        if (!Files.exists(filepath)) {
            return;
        }

        try {
            String data = Files.readString(filepath, StandardCharsets.UTF_8);
            String[] roomsAsString = data.split(System.getProperty("line.separator"));
            for (String roomAsString : roomsAsString) {
                String[] roomData = roomAsString.split(",");
                if (roomData[0] == null || roomData[0].trim().isEmpty()) {
                    continue;
                }
                long id = Long.parseLong(roomData[0]);
                int roomNumber = Integer.parseInt(roomData[1]);
                String bedTypesData = roomData[2];
                String[] bedTypesAsString = bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedTypesAsString.length];
                for (int i = 0; i < bedTypes.length; i++) {
                    switch (bedTypesAsString[i]) {
                        case SystemUtils.SINGLE_BED -> bedTypes[i] = BedType.SINGLE;
                        case SystemUtils.DOUBLE_BED -> bedTypes[i] = BedType.DOUBLE;
                        case SystemUtils.KINGSIZE_BED -> bedTypes[i] = BedType.KINGSIZE;
                    }


                }
                addExistingGuest(id, roomNumber, Arrays.asList(bedTypes));
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(filepath.toString(), "write", "room data");
        }
    }

    private long findNewIdForTheRoom() {
        long max = 0;
        for (Room room : rooms) {
            if (room.getId() > max) {
                max = room.getId();
            }
        }
        return max + 1;
    }

    @Override
    public void remove(long roomId) {
        int roomToBeRemoved = -1;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId() == roomId) {
                roomToBeRemoved = i;
                break;
            }
        }
        if (roomToBeRemoved > -1) {
            rooms.remove(roomToBeRemoved);
        }
    }

    @Override
    public void edit(long roomId, int numberRoom, List<BedType> bedTypes) {
        remove(roomId);
        addExistingGuest(roomId, numberRoom, bedTypes);
    }

    @Override
    public Room getById(long roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                return room;
            }
        }
        return null;
    }
}
