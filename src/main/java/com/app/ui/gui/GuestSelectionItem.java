package com.app.ui.gui;

//tworzymy klasę, która ułatwi nam wybieranie gościa
public class GuestSelectionItem {

    private String firstName;
    private String lasstName;
    private int id;

    //tworzymy konstruktor
    public GuestSelectionItem(String firstName, String lasstName, int id) {
        this.firstName = firstName;
        this.lasstName = lasstName;
        this.id = id;
    }


    //tworzymy konstruktor
    public String getFirstName() {
        return firstName;
    }

    public String getLasstName() {
        return lasstName;
    }

    public int getId() {
        return id;
    }

    //tworzymy toString do poprawnego wyświetlania
    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lasstName);
    }
}
