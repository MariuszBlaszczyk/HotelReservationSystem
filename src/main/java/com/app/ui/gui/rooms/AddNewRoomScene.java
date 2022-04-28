package com.app.ui.gui.rooms;

import com.app.domain.ObjectPool;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import com.app.utils.SystemUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewRoomScene {

    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();

    public AddNewRoomScene(Stage modalStage, TableView<RoomDTO> tableView) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);


        Label numberLabel = new Label("Room number");
        TextField numberField = new TextField();

        numberField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberField.setText(oldValue);
            }
        });

        gridPane.add(numberLabel, 0, 0);
        gridPane.add(numberField, 1, 0);

        Label bedTypeLabel = new Label("Beds types: ");

        Button addNewBadButton = new Button();

        Image addIcon = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("icon add.png")));
        ImageView imageViewAddIcon = new ImageView(addIcon);
        imageViewAddIcon.setFitHeight(16);
        imageViewAddIcon.setFitWidth(16);
        addNewBadButton.setGraphic(imageViewAddIcon);
        addNewBadButton.setPadding(Insets.EMPTY);

        gridPane.add(bedTypeLabel, 0, 1);
        gridPane.add(addNewBadButton, 1, 1);
        gridPane.setVgap(20);

        VBox bedsVerticalLayout = new VBox(getBedTypeFieldCombobox());
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

                    modalStage.close();
                }
        );

        addNewRoomButton.setPadding(new Insets(5, 5, 5, 5));

        gridPane.add(bedsVerticalLayout, 1, 2);
        gridPane.add(addNewRoomButton, 0, 3);


        this.mainScene = new Scene(gridPane, 740, 580);

        this.mainScene.getStylesheets()
                .add(Objects.requireNonNull(getClass()
                                .getClassLoader()
                                .getResource("hotelReservation.css"))
                        .toExternalForm());
    }


    public Scene getMainScene() {
        return mainScene;
    }


    private ComboBox<String> getBedTypeFieldCombobox() {
        ComboBox<String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll(SystemUtils.SINGLE_BED, SystemUtils.DOUBLE_BED, SystemUtils.KINGSIZE_BED);
        bedTypeField.setValue(SystemUtils.SINGLE_BED);
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }
}
