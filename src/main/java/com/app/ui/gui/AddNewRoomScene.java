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
    private final RoomService roomService = ObjectPool.getRoomService();

    public AddNewRoomScene(Stage stage, TableView<RoomDTO> tableView) {

        Label numberLabel = new Label("Room number");
        TextField numberField = new TextField();
        HBox roomNumber = new HBox(numberLabel, numberField);

        Label bedTypeLabel = new Label("Beds types:");
        Button addNewBadButton = new Button("Add new bed");


        HBox bedTypeRow = new HBox(bedTypeLabel, addNewBadButton);

        VBox bedsVerticalLayout = new VBox(bedTypeRow, getBedTypeFieldCombobox());

        addNewBadButton.setOnAction(actionEvent -> bedsVerticalLayout.getChildren().addAll(getBedTypeFieldCombobox()));

        Button addNewRoomButton = new Button("Add new room");
        addNewRoomButton.setOnAction(actionEvent -> {
                    int newRoomNumber = Integer.parseInt(numberField.getText());
                    List<String> bedTypes = new ArrayList<>();
                    this.comboBoxes.forEach(comboBoxes ->
                            bedTypes.add(comboBoxes.getValue()));
                    this.roomService.createNewRoom(newRoomNumber, bedTypes);

                    tableView.getItems().clear();

                    List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
                    tableView.getItems().addAll(allAsDTO);

                    stage.close();
                }
        );

        VBox mainFormLayout = new VBox(roomNumber, bedsVerticalLayout, addNewRoomButton);

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
