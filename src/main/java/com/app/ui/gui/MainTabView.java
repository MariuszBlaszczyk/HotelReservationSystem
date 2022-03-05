package com.app.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainTabView {

    private final TabPane mainTabs;

    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        ReservationsTab reservationsTab = new ReservationsTab();
        GuestsTab guestsTab = new GuestsTab();
        RoomsTab roomsTab = new RoomsTab(primaryStage);


        this.mainTabs.getTabs().addAll(reservationsTab.getReservationTab(), guestsTab.getGuestTab(), roomsTab.getRoomTab());
    }

    TabPane getMainTabs() {
        return mainTabs;
    }


}
