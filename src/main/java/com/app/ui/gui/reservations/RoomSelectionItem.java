package com.app.ui.gui.reservations;


public record RoomSelectionItem(int number, long id) {


    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("%d", this.number);
    }
}
