package com.app.domain.reservation;


import com.app.domain.ObjectPool;
import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.exceptions.PersistenceToFileException;
import com.app.utils.SystemUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository {

    private static final ReservationFileRepository INSTANCE = new ReservationFileRepository();

    GuestService guestService = ObjectPool.getGuestService();
    RoomService roomService = ObjectPool.getRoomService();

    static List<Reservation> reservations = new ArrayList<>();

    private ReservationFileRepository() {
    }

    public static ReservationFileRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation reservation = new Reservation(findNewIdForReservation(), room, guest, from, to);
        reservations.add(reservation);
        return reservation;
    }


    @Override
    public void readAll() {
        String name = "reservations.csv";
        Path file = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), name);
        if (!Files.exists(file)) {
            return;
        }
        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] reservationsAsString = data.split(System.getProperty("line.separator"));
            for (String reservationAsString : reservationsAsString) {
                String[] reservationData = reservationAsString.split(",");
                if (reservationData[0] == null || reservationData[0].trim().isEmpty()) {
                    continue;
                }
                long id = Long.parseLong(reservationData[0]);
                long roomId = Integer.parseInt(reservationData[1]);
                long guestId = Integer.parseInt(reservationData[2]);
                String fromAsString = reservationData[3];
                String toAsString = reservationData[4];
                addExistingReservation(id, this.roomService.getRoomById(roomId),
                        this.guestService.getGuestById(guestId), LocalDateTime.parse(fromAsString),
                        LocalDateTime.parse(toAsString));
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "guests data");
        }
    }


    @Override
    public void writeAll() {
        String name = "reservations.csv";
        Path file = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), name);
        StringBuilder sb = new StringBuilder();
        for (Reservation reservation : reservations) {
            sb.append(reservation.toCSV());
        }
        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "write", "reservation data");
        }
    }


    private void addExistingReservation(long id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation reservation = new Reservation(id, room, guest, from, to);
        reservations.add(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservations;
    }


    @Override
    public void remove(long reservationId) {
        int reservationToBeRemoved = -1;
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).id() == reservationId) {
                reservationToBeRemoved = i;
                break;
            }
        }
        if (reservationToBeRemoved > -1) {
            reservations.remove(reservationToBeRemoved);
        }
    }


    @Override
    public void edit(long reservationId, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime) {
        remove(reservationId);
        addExistingReservation(reservationId, room, guest, fromWithTime, toWithTime);
    }

    long findNewIdForReservation() {
        long max = 0;
        for (Reservation reservation : ReservationFileRepository.reservations) {
            if (reservation.id() > max) {
                max = reservation.id();
            }
        }
        return max + 1;
    }
}
