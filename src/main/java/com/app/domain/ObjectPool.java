package com.app.domain;

import com.app.domain.guest.GuestRepository;
import com.app.domain.guest.GuestService;
import com.app.domain.reservation.ReservationRepository;
import com.app.domain.reservation.ReservationService;
import com.app.domain.room.RoomRepository;
import com.app.domain.room.RoomService;

public class ObjectPool {


    private ObjectPool() {
    }

    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {
        return GuestRepository.getInstance();
    }

    public static RoomService getRoomService() {
        return RoomService.getInstance();
    }

    public static RoomRepository getRoomRepository() {
        return RoomRepository.getInstance();
    }

    public static ReservationService getReservationService() {
        return ReservationService.getInstance();
    }

    public static ReservationRepository getReservationRepository() {
        return ReservationRepository.getInstance();
    }
}
