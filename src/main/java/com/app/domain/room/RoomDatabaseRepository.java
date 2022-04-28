package com.app.domain.room;

import com.app.utils.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseRepository implements RoomRepository {

    private static final RoomRepository INSTANCE = new RoomDatabaseRepository();

    public static RoomRepository getInstance() {
        return INSTANCE;
    }

    private final List<Room> rooms = new ArrayList<>();

    private final String removeBedsTemplate = "DELETE FROM BEDS WHERE ROOM_ID = %d ";
    private final String createBedTemplate = "INSERT INTO BEDS(ROOM_ID, BED) VALUES (%d, '%s')";


    @Override
    public void writeAllRoomsToFile() {
    }

    @Override
    public void readAllRoomsFromFile() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet rsRoom = statement.executeQuery("SELECT * FROM ROOMS");
            while (rsRoom.next()) {
                long id = rsRoom.getLong(1);
                int roomNumber = rsRoom.getInt(2);
                List<BedType> bedTypes = new ArrayList<>();
                this.rooms.add(new Room(id, roomNumber, bedTypes));
            }

            ResultSet rsBeds = statement.executeQuery("SELECT * FROM BEDS");
            while (rsBeds.next()) {
                long roomId = rsBeds.getLong(2);
                String bedType = rsBeds.getString(3);
                BedType bedTypeAsEnum = BedType.SINGLE;
                if (SystemUtils.DOUBLE_BED.equals(bedType)) {
                    bedTypeAsEnum = BedType.DOUBLE;
                } else if (SystemUtils.KINGSIZE_BED.equals(bedType)) {
                    bedTypeAsEnum = BedType.KINGSIZE;
                }
                this.getById(roomId).addBed(bedTypeAsEnum);
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error loading data from database");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Room createNewRoom(int roomNumber, List<BedType> bedTypes) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String insertRoomTemplate = "INSERT INTO ROOMS(ROOM_NUMBER) VALUES (%d)";
            String query = String.format(insertRoomTemplate, roomNumber);
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();

            long newId = -1;
            while (resultSet.next()) {
                newId = resultSet.getLong(1);

            }


            for (BedType bedType : bedTypes) {
                statement.execute(String.format(createBedTemplate, newId, bedType.toString()));
            }

            statement.close();

            Room newRoom = new Room(newId, roomNumber, bedTypes);
            this.rooms.add(newRoom);
            return newRoom;

        } catch (SQLException e) {
            System.out.println("Error when creating a new room.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void remove(long roomId) {

        try {
            Statement statement = SystemUtils.connection.createStatement();

            String removeBedsQuery = String.format(removeBedsTemplate, roomId);
            statement.execute(removeBedsQuery);
            String removeRoomTemplate = "DELETE FROM ROOMS WHERE ID = %d";
            String removeRoomQuery = String.format(removeRoomTemplate, roomId);
            statement.execute(removeRoomQuery);
            statement.close();
            this.removeById(roomId);

        } catch (SQLException e) {
            System.out.println("An error occurred while deleting data.");
            throw new RuntimeException(e);
        }

    }

    private void removeById(long roomId) {
        int indexToBeRemoved = -1;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getId() == roomId) {
                indexToBeRemoved = i;
            }
        }
        this.rooms.remove(indexToBeRemoved);
    }


    @Override
    public void edit(long roomId, int numberRoom, List<BedType> bedTypes) {

        try {
            Statement statement = SystemUtils.connection.createStatement();
            String updateRoomTemplate = "UPDATE ROOMS SET ROOM_NUMBER=%d WHERE ID=%d ";
            String updateRoomQuery = String.format(updateRoomTemplate, numberRoom, roomId);
            statement.executeUpdate(updateRoomQuery);

            String deleteBedsQuery = String.format(removeBedsTemplate, roomId);
            statement.execute(deleteBedsQuery);

            for (BedType bedType : bedTypes) {
                String createQuery = String.format(createBedTemplate, roomId, bedType.toString());
                statement.execute(createQuery);
            }

            statement.close();

            Room roomToBeUpdated = getById(roomId);
            roomToBeUpdated.setNumber(numberRoom);
            roomToBeUpdated.setBeds(bedTypes);


        } catch (SQLException e) {
            System.out.println("An error occurred while modifying data.");
            throw new RuntimeException(e);
        }
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
