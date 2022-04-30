package com.app.domain.room;

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
}
