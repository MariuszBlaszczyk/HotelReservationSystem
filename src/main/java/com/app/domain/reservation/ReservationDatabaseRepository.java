package com.app.domain.reservation;

import com.app.domain.ObjectPool;
import com.app.domain.guest.Guest;
import com.app.domain.guest.GuestService;
import com.app.domain.room.Room;
import com.app.domain.room.RoomService;
import com.app.utils.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationDatabaseRepository implements ReservationRepository {

    private final static ReservationRepository INSTANCE = new ReservationDatabaseRepository();
    private final RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final List<Reservation> reservations = new ArrayList<>();

    public static ReservationRepository getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        try {
            String fromAsStr = from.format(DateTimeFormatter.ISO_DATE_TIME); // yyyy-MM-dd HH:mm:ss
            String toAsStr = to.format(DateTimeFormatter.ISO_DATE_TIME);
            Statement statement = SystemUtils.connection.createStatement();
            String createTemplate = "INSERT INTO RESERVATIONS(ROOM_ID, GUEST_ID, RES_FROM, RES_TO) VALUES (%d, %d, '%s', '%s')";
            String createQuery = String.format(createTemplate,room.getId(), guest.getId(), fromAsStr, toAsStr);
            statement.execute(createQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            long id=-1;
            while(rs.next()) {
                id = rs.getLong(1);
            }

            Reservation newReservation = new Reservation(id, room, guest, from, to);
            this.reservations.add(newReservation);
            return newReservation;
        } catch (SQLException e) {
            System.out.println("Error while creating a reservation.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM RESERVATIONS");

            while(rs.next()) {
                long id = rs.getLong(1);
                long roomId = rs.getLong(2);
                long guestId = rs.getLong(3);

                String fromAsString = rs.getString(4);
                String toAsString = rs.getString(5);

                LocalDateTime from = LocalDateTime.parse(fromAsString.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);
                LocalDateTime to = LocalDateTime.parse(toAsString.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);


                Reservation newReservation = new Reservation(id, this.roomService.getRoomById(roomId), this.guestService.getGuestById(guestId), from, to);
                this.reservations.add(newReservation);
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error reading the reservation.");
            throw new RuntimeException(e);
        }

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
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeTemplate = "DELETE FROM RESERVATIONS WHERE ID=%d";
            String removeQuery = String.format(removeTemplate, reservationId);
            statement.execute(removeQuery);
            statement.close();

            this.removeById(reservationId);
        } catch (SQLException e) {
            System.out.println("Błąd przy usuwaniu rezerwacji");
            throw new RuntimeException(e);
        }
    }



    private void removeById(long id) {
        int indexToBeRemoved = -1;

        for(int i=0; i<this.reservations.size();i++) {
            if(this.reservations.get(i).getId()==id) {
                indexToBeRemoved = i;
                break;
            }
        }

        this.reservations.remove(indexToBeRemoved);
    }

    @Override
    public void edit(long reservationId, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime) {

    }
}
