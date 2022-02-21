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
        Tab roomTab = new Tab("Rooms", new Label("Room service"));
        roomTab.setClosable(false);

        this.mainTabs.getTabs().addAll(reservationTab, guestTab, roomTab);
    }

    TabPane getMainTabs() {
        return mainTabs;
    }


}
