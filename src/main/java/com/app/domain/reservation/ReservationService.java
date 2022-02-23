package com.app.domain.reservation;

import com.app.domain.ObjectPool;
import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.reservation.dto.ReservationDTO;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final static ReservationService INSTANCE = new ReservationService();

    private final RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final ReservationRepository reservationRepository = ObjectPool.getReservationRepository();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        return INSTANCE;
    }

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
        reservationRepository.readAll();
    }

    public void writeAllReservationsToFile() {
        reservationRepository.writeAll();
    }

    public void removeReservationFromList(int reservationId) {
        reservationRepository.remove(reservationId);
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

    public List<ReservationDTO> getReservationsAsDTO() {
        List<ReservationDTO> result = new ArrayList<>();

        List<Reservation> reservations = ReservationRepository.getAll();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = reservation.generateDTO();
            result.add(reservationDTO);
        }
        return result;
    }
}
