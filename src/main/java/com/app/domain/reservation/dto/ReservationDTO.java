package com.app.domain.reservation.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ReservationDTO {
    private final long id;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final long roomId;
    private final int roomNumber;
    private final long guestId;
    private final String guestName;

    public ReservationDTO(long id, LocalDateTime from, LocalDateTime to, long roomId,
                          int roomNumber, long guestId, String guestName) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.guestName = guestName;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public long getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public long getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ReservationDTO) obj;
        return this.id == that.id &&
                Objects.equals(this.from, that.from) &&
                Objects.equals(this.to, that.to) &&
                this.roomId == that.roomId &&
                this.roomNumber == that.roomNumber &&
                this.guestId == that.guestId &&
                Objects.equals(this.guestName, that.guestName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, roomId, roomNumber, guestId, guestName);
    }

    @Override
    public String toString() {
        return "ReservationDTO[" +
                "id=" + id + ", " +
                "from=" + from + ", " +
                "to=" + to + ", " +
                "roomId=" + roomId + ", " +
                "roomNumber=" + roomNumber + ", " +
                "guestId=" + guestId + ", " +
                "guestName=" + guestName + ']';
    }

}



