package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.room.Room;

import java.time.LocalDateTime;

public record Reservation(int id, Room room, Guest guest, LocalDateTime from,
                          LocalDateTime to) {


    String toCSV() {
        return String.format("%d,%s,%s,%s,%s%s",
                this.id,
                this.room.id(),
                this.guest.id(),
                this.from.toString(),
                this.to.toString(),
                System.getProperty("line.separator"));
    }
}
