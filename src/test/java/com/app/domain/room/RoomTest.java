package com.app.domain.room;

import com.app.domain.room.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomTest {

    @Test
    public void toCSVTest() {
        List<BedType> bedTypeList = Arrays.asList(BedType.values());

        Room room = new Room(1, 10, bedTypeList);

        String csvTemplate = "1,10,Single#Double#Kingsize" + System.getProperty("line.separator");
        String createdCSV = room.toCSV();

        assertEquals(csvTemplate, createdCSV, "Comparison of the generated CSV formats");
    }


    @Test
    public void toCVSWithNullBedList() {
        Room room = new Room(1, 10, null);

        assertNotNull(room.getBeds());

        String csvTemplate = "1,10," + System.getProperty("line.separator");

        String createdCSV = room.toCSV();

        assertEquals(csvTemplate, createdCSV, "Comparison of generated CSV formats when bed list == null");
    }


    @Test
    public void toDTOTest() {
        List<BedType> bedTypeList = Arrays.asList(BedType.values());

        Room room = new Room(1, 10, bedTypeList);

        RoomDTO roomDTO = room.generateDTO();

        assertEquals(roomDTO.getId(), 1);
        assertEquals(roomDTO.getNumber(), 10);
        assertEquals(roomDTO.beds(), "Single,Double,Kingsize");
        assertEquals(roomDTO.bedsCount(), 3);
        assertEquals(roomDTO.roomSize(), 7);
    }


    @Test
    public void toDTOFromRoomWithNullBedsList() {

        Room room = new Room(1, 10, null);

        RoomDTO roomDTO = room.generateDTO();

        assertEquals(roomDTO.getId(), 1);
        assertEquals(roomDTO.getNumber(), 10);
        assertEquals(roomDTO.beds(), "");
        assertEquals(roomDTO.bedsCount(), 0);
        assertEquals(roomDTO.roomSize(), 0);
    }

}

