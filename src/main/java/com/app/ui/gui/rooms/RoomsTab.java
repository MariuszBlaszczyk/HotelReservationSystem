package com.app.ui.gui.rooms;

import com.app.domain.ObjectPool;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class RoomsTab {

    private final Tab roomTab;
    private final RoomService roomService = ObjectPool.getRoomService();
    private final Stage primaryStage;

    public RoomsTab(Stage primaryStage) {

        TableView<RoomDTO> tableView = getRoomDTOTableView();
        this.primaryStage = primaryStage;
        Button button = new Button("Create new");

        button.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);


            stage.setScene(new AddNewRoomScene(stage, tableView).getMainScene());

            stage.initOwner(this.primaryStage);
            stage.setTitle("Add new room");
            stage.showAndWait();
        });

        VBox layout = new VBox(button, tableView);

        this.roomTab = new Tab("Rooms", layout);
        roomTab.setClosable(false);
    }


    public Tab getRoomTab() {
        return roomTab;
    }

    private TableView<RoomDTO> getRoomDTOTableView() {
        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, Integer> roomSizeColumn = new TableColumn<>("Room size");
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));

        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Number of beds");
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

        TableColumn<RoomDTO, String> bedsTypesColumn = new TableColumn<>("Bed types");
        bedsTypesColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));


        TableColumn<RoomDTO, RoomDTO> actionColumn = new TableColumn<>();
        actionColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper<>(value.getValue()));


        actionColumn.setCellFactory(param -> new TableCell<>() {

            final Button deleteButton = new Button("Delete");
            final Button editButton = new Button("Edit");
            final HBox hBox = new HBox(deleteButton, editButton);


            @Override
            protected void updateItem(RoomDTO value, boolean empty) {
                super.updateItem(value, empty);

                if (value == null) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                    deleteButton.setOnAction(actionEvent -> {
                        roomService.removeRoomFromList(value.getId());
                        tableView.getItems().remove(value);
                    });
                    editButton.setOnAction(actionEvent -> {
                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.setScene(new EditRoomScene(stage, tableView, value).getMainScene());
                        stage.initOwner(primaryStage);
                        stage.setTitle("Edit room");
                        stage.showAndWait();
                    });
                }
            }
        });

        tableView.getColumns().addAll(numberColumn, roomSizeColumn, bedsCountColumn, bedsTypesColumn, actionColumn);

        RoomService roomService = ObjectPool.getRoomService();
        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        tableView.getItems().addAll(allAsDTO);
        return tableView;
    }

}
