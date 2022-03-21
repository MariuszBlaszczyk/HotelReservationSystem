package com.app.ui.gui;

//tworzymy klasę, która ułatwi nam wybieranie pokoju
public class RoomSelectionItem {

    private int number; // numer pokoju
    private int id; // id pokoju

    //tworzymy konstruktor
    public RoomSelectionItem(int number, int id) {
        this.number = number;
        this.id = id;
    }

    //tworzymy konstruktor
    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    //tworzymy toString do poprawnego wyświetlania
    @Override
    public String toString() {
        return String.format("%d", this.number);
    }
}
