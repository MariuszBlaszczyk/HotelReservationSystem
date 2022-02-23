package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.reservation.ReservationService;
import com.app.domain.reservation.dto.ReservationDTO;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;

public class ReservationsTab {

    private final Tab reservationTab;

    public ReservationsTab() {
        TableView<ReservationDTO> tableView = new TableView<>();

        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("From");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("To");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<ReservationDTO, Integer> roomNumberColumn = new TableColumn<>("Room number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        TableColumn<ReservationDTO, String> guestNameColumn = new TableColumn<>("Guest name");
        guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("guestName"));

        ReservationService reservationService = ObjectPool.getReservationService();
        tableView.getItems().addAll(reservationService.getReservationsAsDTO());


        tableView.getColumns().addAll(fromColumn, toColumn, roomNumberColumn, guestNameColumn);


        this.reservationTab = new Tab("Reservations", tableView);
        reservationTab.setClosable(false);
    }


    public Tab getReservationTab() {
        return reservationTab;
    }
}
