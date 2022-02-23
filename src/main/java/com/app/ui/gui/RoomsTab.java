package com.app.ui.gui;

import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RoomsTab {

    private Tab roomTab;
    private RoomService roomService = new RoomService();

    public RoomsTab() {

        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Bed types");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        tableView.getColumns().addAll(numberColumn, bedsColumn);

        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        tableView.getItems().addAll(allAsDTO);

        this.roomTab = new Tab("Rooms", tableView);
        roomTab.setClosable(false);
    }

    public Tab getRoomTab() {
        return roomTab;
    }

}
