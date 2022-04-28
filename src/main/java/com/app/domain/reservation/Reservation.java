package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.reservation.dto.ReservationDTO;
import com.app.domain.room.Room;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Reservation {
    private final long id;
    private final Room room;
    private final Guest guest;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Reservation(long id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.from = from;
        this.to = to;
    }

    public long getId() {
        return this.id;
    }

    String toCSV() {
        return String.format("%d,%s,%s,%s,%s%s",
                this.id,
                this.room.getId(),
                this.guest.getId(),
                this.from.toString(),
                this.to.toString(),
                System.getProperty("line.separator"));
    }



    public ReservationDTO getAsDTO() {
        String guestName = this.guest.getFirstName() + " " + this.guest.getLastName();
        return new ReservationDTO(this.id, this.from, this.to, this.room.getId(), this.room.getNumber(),
                this.guest.getId(), guestName);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Reservation) obj;
        return this.id == that.id &&
                Objects.equals(this.room, that.room) &&
                Objects.equals(this.guest, that.guest) &&
                Objects.equals(this.from, that.from) &&
                Objects.equals(this.to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room, guest, from, to);
    }

    @Override
    public String toString() {
        return "Reservation id = " + id + "\n" +
                room +
                "- guest: " + guest + "\n" +
                "- from: " + from + "\n" +
                "- to: " + to;
    }

}
