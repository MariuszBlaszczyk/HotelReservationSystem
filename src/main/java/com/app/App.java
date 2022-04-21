package com.app;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.reservation.ReservationService;
import com.app.domain.room.RoomService;
import com.app.ui.gui.PrimaryStage;
import com.app.ui.text.TextUI;
import com.app.utils.SystemUtils;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    private static final TextUI TEXT_UI = new TextUI();

    private static final GuestService GUEST_SERVICE = ObjectPool.getGuestService();
    private static final RoomService ROOM_SERVICE = ObjectPool.getRoomService();
    private static final ReservationService RESERVATION_SERVICE = ObjectPool.getReservationService();

    public static void main(String[] args) {

        SystemUtils systemUtils = new SystemUtils();
        SystemUtils.createDataDirectory();
        systemUtils.createDatabaseConnection();
        System.out.println("Loading data in progress...");
        GUEST_SERVICE.readAllGuestsFromFile();
        ROOM_SERVICE.readAllRoomsFromFile();
        RESERVATION_SERVICE.readAllReservationsFromFile();


        Application.launch(args);

//        TEXT_UI.showSystemInfo();
//        TEXT_UI.showMainMenu();

    }

    @Override
    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);
    }

    @Override
    public void stop() {
        System.out.println("I am leaving the application. Saves the data to a file.");
        GUEST_SERVICE.writeAllGuestsToFile();
        ROOM_SERVICE.writeAllRoomsToFile();
        RESERVATION_SERVICE.writeAllReservationsToFile();
    }

}

