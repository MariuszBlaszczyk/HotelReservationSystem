package com.app.ui.gui;


public class GuestSelectionItem {

    private String firstName;
    private String lasstName;
    private int id;


    public GuestSelectionItem(String firstName, String lasstName, int id) {
        this.firstName = firstName;
        this.lasstName = lasstName;
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLasstName() {
        return lasstName;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lasstName);
    }
}
