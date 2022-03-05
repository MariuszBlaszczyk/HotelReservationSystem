package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class RoomsTab {

    private final Tab roomTab;

    public RoomsTab(Stage primaryStage) {

        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, Integer> roomSizeColumn = new TableColumn<>("Room size");
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));

        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Number of beds");
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

        TableColumn<RoomDTO, String> bedsTypesColumn = new TableColumn<>("Bed types");
        bedsTypesColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        tableView.getColumns().addAll(numberColumn, roomSizeColumn, bedsCountColumn, bedsTypesColumn);

        RoomService roomService = ObjectPool.getRoomService();
        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        tableView.getItems().addAll(allAsDTO);

        Button button = new Button("Create new");

        button.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);

            Label numberLabel = new Label("Room number");// dodajemy napis Room number, żebyśmy wiedzieli co należy wpisać
            TextField numberField = new TextField();//tworzymy pole tekstowe
            HBox roomNumber = new HBox(numberLabel, numberField);//tworzymy horyzontalny box umieszczamy label i textfield
            Scene newRoomScene = new Scene(roomNumber, 740, 580);//tworzymy nową scenę z rozmiarem i dodajemy HBox
            stage.setScene(newRoomScene);

            stage.initOwner(primaryStage);
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

}
