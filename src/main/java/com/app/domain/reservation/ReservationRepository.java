package com.app.domain.reservation;


import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.exceptions.PersistenceToFileException;
import com.app.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    GuestService guestService = new GuestService();
    RoomService roomService = new RoomService();

    List<Reservation> reservations = new ArrayList<>();

    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation reservation = new Reservation(findNewIdForReservation(), room, guest, from, to);
        reservations.add(reservation);
        return reservation;
    }

    private int findNewIdForReservation() {
        int max = 0;
        for (Reservation reservation : this.reservations) {
            if (reservation.id() > max) {
                max = reservation.id();
            }
        }
        return max + 1;
    }



    public void readAll() {
        String name = "reservations.csv";
        Path file = Paths.get(Utils.DATA_DIRECTORY.toString(), name);
        if (!Files.exists(file)) {
            return;
        }
        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] reservationsAsString = data.split(System.getProperty("line.separator"));
            for (String reservationAsString : reservationsAsString) {
                try {
                    String[] reservationData = reservationAsString.split(",");
                    int id = Integer.parseInt(reservationData[0]);
                    int roomId = Integer.parseInt(reservationData[1]);
                    int guestId = 0;
                    try {
                        guestId = Integer.parseInt(reservationData[2]);
                    } catch (Exception e) {
                        System.out.println("No guest, defaulting to 0");
                    }
                    String fromAsString = reservationData[3];
                    String toAsString = reservationData[4];
                    addExistingReservation(id, this.roomService.getRoomById(roomId),
                            this.guestService.getGuestById(guestId), LocalDateTime.parse(fromAsString),
                            LocalDateTime.parse(toAsString));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Wrong data for reservation, reading next line");
                }
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "guests data");
        }
    }


    public void writeAll() {
        String name = "reservations.csv";
        Path file = Paths.get(Utils.DATA_DIRECTORY.toString(), name);
        StringBuilder sb = new StringBuilder();
        for (Reservation reservation : this.reservations) {
            sb.append(reservation.toCSV());
        }
        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "write", "reservation data");
        }
    }


    private void addExistingReservation(int id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation reservation = new Reservation(id, room, guest, from, to);
        reservations.add(reservation);
    }
}
