package com.app.ui.gui;

import javafx.scene.control.TabPane;

public class MainTabView {

    private final TabPane mainTabs;

    public MainTabView() {
        this.mainTabs = new TabPane();

        ReservationsTab reservationsTab = new ReservationsTab();
        GuestsTab guestsTab = new GuestsTab();
        RoomsTab roomsTab = new RoomsTab();


        this.mainTabs.getTabs().addAll(reservationsTab.getReservationTab(), guestsTab.getGuestTab(), roomsTab.getRoomTab());
    }

    TabPane getMainTabs() {
        return mainTabs;
    }


}
