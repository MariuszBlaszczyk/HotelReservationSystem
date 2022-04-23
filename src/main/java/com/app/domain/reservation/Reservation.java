package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.reservation.dto.ReservationDTO;
import com.app.domain.room.Room;

import java.time.LocalDateTime;

public record Reservation(long id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {


    String toCSV() {
        return String.format("%d,%s,%s,%s,%s%s",
                this.id,
                this.room.id(),
                this.guest.id(),
                this.from.toString(),
                this.to.toString(),
                System.getProperty("line.separator"));
    }

    @Override
    public String toString() {
        return "Reservation id = " + id + "\n" +
                room +
                "- guest: " + guest + "\n" +
                "- from: " + from + "\n" +
                "- to: " + to;
    }

    public ReservationDTO generateDTO() {
        String guestName = this.guest.firstName() + " " + this.guest.lastName();
        return new ReservationDTO(this.id, this.from, this.to, this.room.id(), this.room.number(), this.guest.id(), guestName);
    }
}
