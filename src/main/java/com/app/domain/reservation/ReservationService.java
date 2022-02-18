package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationService {

    private final RoomService roomService = new RoomService();
    private final GuestService guestService = new GuestService();
    private final ReservationRepository reservationRepository = new ReservationRepository();

    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) throws IllegalArgumentException {
        Room room = roomService.getRoomById(roomId);
        Guest guest = guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Utils.HOTEL_NIGHT_START_HOUR, Utils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Utils.HOTEL_NIGHT_END_HOUR, Utils.HOTEL_NIGHT_END_MINUTE);

        if (toWithTime.isBefore(fromWithTime)) {
            throw new IllegalArgumentException();
        }

        return reservationRepository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }

    public void readAllReservationsFromFile() {
        this.reservationRepository.readAll();
    }

    public void writeAllReservationsToFile() {
        this.reservationRepository.writeAll();
    }

    public void removeReservationFromList(int reservationId) {
        this.reservationRepository.remove(reservationId);
    }

    public void editReservation(int reservationId, LocalDate from, LocalDate to, int roomId, int guestId) {
        Room room = roomService.getRoomById(roomId);
        Guest guest = guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Utils.HOTEL_NIGHT_START_HOUR, Utils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Utils.HOTEL_NIGHT_END_HOUR, Utils.HOTEL_NIGHT_END_MINUTE);

        if (toWithTime.isBefore(fromWithTime)) {
            throw new IllegalArgumentException();
        }

        reservationRepository.edit(reservationId, room, guest, fromWithTime, toWithTime);
    }

    public List<Reservation> getAllReservations() {
        return ReservationRepository.getAll();
    }
}
