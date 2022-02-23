package com.app.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainTabView {

    private TabPane mainTabs;

    public MainTabView() {
        this.mainTabs = new TabPane();

        Tab reservationTab = new Tab("Reservations", new Label("Reservation handling"));
        reservationTab.setClosable(false);
        Tab guestTab = new Tab("Guests", new Label("Guest service"));
        guestTab.setClosable(false);

        RoomsTab roomsTab = new RoomsTab();

        this.mainTabs.getTabs().addAll(reservationTab, guestTab, roomsTab.getRoomTab());
    }

    TabPane getMainTabs() {
        return mainTabs;
    }


}
