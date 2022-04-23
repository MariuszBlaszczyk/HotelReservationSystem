package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {


    Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to);


    void readAll();

    void writeAll();


    List<Reservation> getAll();

    void remove(long reservationId);

    void edit(long reservationId, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime);
}
