package com.app;

import com.app.domain.guest.GuestService;
import com.app.domain.reservation.ReservationService;
import com.app.domain.room.RoomService;
import com.app.ui.gui.PrimaryStage;
import com.app.ui.text.TextUI;
import com.app.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    private static final TextUI TEXT_UI = new TextUI();
    private static final GuestService GUEST_SERVICE = new GuestService();
    private static final RoomService ROOM_SERVICE = new RoomService();
    private static final ReservationService RESERVATION_SERVICE = new ReservationService();

    public static void main(String[] args) {

        Utils.createDataDirectory();
        System.out.println("Loading data in progress...");
        GUEST_SERVICE.readAllGuestsFromFile();
        ROOM_SERVICE.readAllRoomsFromFile();
        RESERVATION_SERVICE.readAllReservationsFromFile();

        Application.launch(args);

    }

    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);
    }

}

