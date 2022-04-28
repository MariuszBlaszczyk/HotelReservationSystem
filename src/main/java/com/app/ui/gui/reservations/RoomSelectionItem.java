package com.app.ui.gui.reservations;


import java.util.Objects;

public final class RoomSelectionItem {
    private final int number;
    private final long id;

    public RoomSelectionItem(int number, long id) {
        this.number = number;
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public int number() {
        return number;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RoomSelectionItem) obj;
        return this.number == that.number &&
                this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, id);
    }

    @Override
    public String toString() {
        return String.format("%d", this.number);
    }

}
