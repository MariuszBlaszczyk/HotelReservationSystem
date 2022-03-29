package com.app.ui.gui;

import com.app.ui.gui.guests.GuestsTab;
import com.app.ui.gui.reservations.ReservationsTab;
import com.app.ui.gui.rooms.RoomsTab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainTabView {

    private final TabPane mainTabs;

    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        ReservationsTab reservationsTab = new ReservationsTab(primaryStage);
        GuestsTab guestsTab = new GuestsTab(primaryStage);
        RoomsTab roomsTab = new RoomsTab(primaryStage);


        this.mainTabs.getTabs().addAll(reservationsTab.getReservationTab(), guestsTab.getGuestTab(), roomsTab.getRoomTab());
    }

    TabPane getMainTabs() {
        return mainTabs;
    }


}
