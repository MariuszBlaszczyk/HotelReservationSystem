package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RoomsTab {

    private final Tab roomTab;

    public RoomsTab() {

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

        this.roomTab = new Tab("Rooms", tableView);
        roomTab.setClosable(false);
    }

    public Tab getRoomTab() {
        return roomTab;
    }

}
