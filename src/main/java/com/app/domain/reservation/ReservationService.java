package com.app.domain.reservation;

import com.app.domain.ObjectPool;
import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.reservation.dto.ReservationDTO;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.utils.SystemUtils;

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

    public Reservation createNewReservation(LocalDate from, LocalDate to, long roomId, long guestId) throws IllegalArgumentException {
        Room room = roomService.getRoomById(roomId);
        Guest guest = guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(SystemUtils.HOTEL_NIGHT_START_HOUR, SystemUtils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(SystemUtils.HOTEL_NIGHT_END_HOUR, SystemUtils.HOTEL_NIGHT_END_MINUTE);

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

    public void removeReservationFromList(long reservationId) {
        reservationRepository.remove(reservationId);
    }

    public void editReservation(long reservationId, LocalDate from, LocalDate to, long roomId, long guestId) {
        Room room = roomService.getRoomById(roomId);
        Guest guest = guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(SystemUtils.HOTEL_NIGHT_START_HOUR, SystemUtils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(SystemUtils.HOTEL_NIGHT_END_HOUR, SystemUtils.HOTEL_NIGHT_END_MINUTE);

        if (toWithTime.isBefore(fromWithTime)) {
            throw new IllegalArgumentException();
        }

        reservationRepository.edit(reservationId, room, guest, fromWithTime, toWithTime);
    }

    public List<Reservation> getAllReservations() {
        return this.reservationRepository.getAll();
    }

    public List<ReservationDTO> getReservationsAsDTO() {
        List<ReservationDTO> result = new ArrayList<>();

        List<Reservation> reservations = this.reservationRepository.getAll();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = reservation.generateDTO();
            result.add(reservationDTO);
        }
        return result;
    }
}
