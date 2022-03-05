package com.app.ui.gui;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddNewRoomScene {

    private Scene mainScene;


    public AddNewRoomScene() {

        Label numberLabel = new Label("Room number");
        TextField numberField = new TextField();
        HBox roomNumber = new HBox(numberLabel, numberField);

        Label bedTypeLabel = new Label("Bed type:");
        ComboBox bedTypeField = new ComboBox();//combobox pozwala utworzyć pole z opcją dokonania wyboru
        bedTypeField.getItems().addAll("Single", "Double", "King size");//dodajemy nazwy typów łóżek
        bedTypeField.setValue("Single");//ustawiamy domyślną wartość, która jako pierwsza wyświetli nam się przy wyborze
        HBox bedType = new HBox(bedTypeLabel, bedTypeField);//tworzymy Hbox, który zgrupuje nam te informacje

        VBox vbox = new VBox(roomNumber, bedType);//tworzymy Vbox, który przyjmuje obydwa HBoxy, które nam pogrupuje

        this.mainScene = new Scene(vbox, 740, 580);//przesyłamy VBox do sceny
    }


    public Scene getMainScene() {
        return mainScene;
    }
}
