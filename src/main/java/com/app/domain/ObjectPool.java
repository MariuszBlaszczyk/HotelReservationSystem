package com.app.domain;

import com.app.domain.guest.GuestDatabaseRepository;
import com.app.domain.guest.GuestRepository;
import com.app.domain.guest.GuestService;
import com.app.domain.reservation.ReservationDatabaseRepository;
import com.app.domain.reservation.ReservationRepository;
import com.app.domain.reservation.ReservationService;
import com.app.domain.room.RoomDatabaseRepository;
import com.app.domain.room.RoomRepository;
import com.app.domain.room.RoomService;

public class ObjectPool {


    private ObjectPool() {
    }

    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {
        return GuestDatabaseRepository.getINSTANCE();
        //return GuestFileRepository.getInstance();
    }

    public static RoomService getRoomService() {
        return RoomService.getInstance();
    }

    public static RoomRepository getRoomRepository() {
//        return RoomFileRepository.getINSTANCE();
        return RoomDatabaseRepository.getInstance();
    }

    public static ReservationService getReservationService() {
        return ReservationService.getInstance();
    }

    public static ReservationRepository getReservationRepository() {
        //return ReservationFileRepository.getInstance();
        return ReservationDatabaseRepository.getINSTANCE();
    }
}
