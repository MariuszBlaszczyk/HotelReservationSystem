package com.app.ui.gui.reservations;


public record GuestSelectionItem(String firstName, String lastName, long id) {


    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
