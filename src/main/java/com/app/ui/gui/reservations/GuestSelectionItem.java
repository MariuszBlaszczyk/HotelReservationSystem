package com.app.ui.gui.reservations;


import java.util.Objects;

public final class GuestSelectionItem {
    private final String firstName;
    private final String lastName;
    private final long id;

    public GuestSelectionItem(String firstName, String lastName, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }


    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public long id() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GuestSelectionItem) obj;
        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }

}
