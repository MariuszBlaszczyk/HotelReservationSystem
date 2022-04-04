package com.app.ui.gui.reservations;

import com.app.domain.ObjectPool;
import com.app.domain.reservation.ReservationService;
import com.app.domain.reservation.dto.ReservationDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ReservationsTab {

    private final Tab reservationTab;
    private final ReservationService reservationService = ObjectPool.getReservationService();

    public ReservationsTab(Stage primaryStage) {
        TableView<ReservationDTO> tableView = getReservationDTOTableView();

        Button button = new Button("Create reservation");

        button.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);


            stage.setScene(new AddNewReservationScene(stage, tableView).getMainScene());

            stage.initOwner(primaryStage);
            stage.setTitle("Create reservation");
            stage.showAndWait();
        });

        VBox layout = new VBox(button, tableView);

        this.reservationTab = new Tab("Reservations", layout);
        reservationTab.setClosable(false);
    }

    private TableView<ReservationDTO> getReservationDTOTableView() {
        TableView<ReservationDTO> tableView = new TableView<>();

        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("From");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("To");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<ReservationDTO, Integer> roomNumberColumn = new TableColumn<>("Room number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        TableColumn<ReservationDTO, String> guestNameColumn = new TableColumn<>("Guest name");
        guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("guestName"));

        TableColumn<ReservationDTO, ReservationDTO> deleteColumn = new TableColumn<>();
        deleteColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper<>(value.getValue()));


        deleteColumn.setCellFactory(param -> new TableCell<>() {

            final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(ReservationDTO value, boolean empty) {
                super.updateItem(value, empty);

                if (value == null) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(actionEvent -> {
                        reservationService.removeReservationFromList(value.getId());
                        tableView.getItems().remove(value);
                    });
                }
            }
        });


        tableView.getItems().addAll(reservationService.getReservationsAsDTO());


        tableView.getColumns().addAll(fromColumn, toColumn, roomNumberColumn, guestNameColumn, deleteColumn);
        return tableView;
    }


    public Tab getReservationTab() {
        return reservationTab;
    }
}
