package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddNewRoomScene {

    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();//tworzymy dostęp do room service

    public AddNewRoomScene(Stage stage, TableView<RoomDTO> tableView) {

        Label numberLabel = new Label("Room number");
        TextField numberField = new TextField();
        HBox roomNumber = new HBox(numberLabel, numberField);

        Label bedTypeLabel = new Label("Beds types:");
        Button addNewBadButton = new Button("Add new bed");


        HBox bedTypeRow = new HBox(bedTypeLabel, addNewBadButton);

        VBox bedsVerticalLayout = new VBox(bedTypeRow, getBedTypeFieldCombobox());

        addNewBadButton.setOnAction(actionEvent -> bedsVerticalLayout.getChildren().addAll(getBedTypeFieldCombobox()));

        Button addNewRoomButton = new Button("Add new room");//dodajemy guzik do dodawania nowego pokoju
        addNewRoomButton.setOnAction(actionEvent -> {
                    int newRoomNumber = Integer.parseInt(numberField.getText());//pobieramy numer pokoju z label utworzonego wyżej
                    List<String> bedTypes = new ArrayList<>();//tworzymy listę, do której będziemy dopisywać typy łóżek
                    this.comboBoxes.forEach(comboBoxes ->
                            bedTypes.add(comboBoxes.getValue()));
                    this.roomService.createNewRoom(newRoomNumber, bedTypes);//listę Stringów dodajemy tutaj

                    tableView.getItems().clear();//czyści tableView

                    List<RoomDTO> allAsDTO = roomService.getAllAsDTO();//pobierz elementy
                    tableView.getItems().addAll(allAsDTO);//dodaj elementy które już były

                    stage.close();//po kliknięciu dodaj nowy pokój zamyka okno dodawania
                }
        );

        VBox mainFormLayout = new VBox(roomNumber, bedsVerticalLayout, addNewRoomButton);//dodajemy guzik do layoutu

        this.mainScene = new Scene(mainFormLayout, 740, 580);
    }


    public Scene getMainScene() {
        return mainScene;
    }


    private ComboBox<String> getBedTypeFieldCombobox() {
        ComboBox<String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll("Single", "Double", "King size");
        bedTypeField.setValue("Single");
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }
}
