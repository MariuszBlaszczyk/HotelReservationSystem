package com.app.domain.reservation;

import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationService {

    private final RoomService roomService = new RoomService();
    private final GuestService guestService = new GuestService();
    private final ReservationRepository reservationRepository = new ReservationRepository();

    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) {
        Room room = roomService.getRoomById(roomId);
        Guest guest = guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Utils.HOTEL_NIGHT_START_HOUR, Utils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Utils.HOTEL_NIGHT_END_HOUR, Utils.HOTEL_NIGHT_END_MINUTE);

        return reservationRepository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }

    public void readAllReservationsFromFile() {
        this.reservationRepository.readAll();
    }

    public void writeAllReservationsToFile() {
        this.reservationRepository.writeAll();
    }
}
