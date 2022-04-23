package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.room.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDatabaseRepository implements ReservationRepository {

    private final static ReservationRepository INSTANCE = new ReservationDatabaseRepository();
    private final List<Reservation> reservations = new ArrayList<>();

    public static ReservationRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public void readAll() {

    }

    @Override
    public void writeAll() {

    }

    @Override
    public List<Reservation> getAll() {
        return this.reservations;
    }

    @Override
    public void remove(long reservationId) {

    }

    @Override
    public void edit(long reservationId, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime) {

    }
}
