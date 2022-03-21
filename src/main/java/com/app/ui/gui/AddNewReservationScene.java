package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import com.app.domain.reservation.ReservationService;
import com.app.domain.reservation.dto.ReservationDTO;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewReservationScene {

    private final Scene mainScene;
    private final RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final ReservationService reservationService = ObjectPool.getReservationService();

    public AddNewReservationScene(Stage modalStage, TableView<ReservationDTO> tableView) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);
        Label fromDateLabel = new Label("Start date of reservation: ");
        DatePicker fromDateField = new DatePicker();

        gridPane.add(fromDateLabel, 0, 0);
        gridPane.add(fromDateField, 1, 0);

        Label toDateLabel = new Label("End date of reservation: ");
        DatePicker toDateField = new DatePicker();

        gridPane.add(toDateLabel, 0, 1);
        gridPane.add(toDateField, 1, 1);


        List<RoomDTO> allRoomsAsDTO = this.roomService.getAllAsDTO();

        List<RoomSelectionItem> roomSelectionItems = new ArrayList<>();

        allRoomsAsDTO.forEach(roomDTO -> roomSelectionItems.add(new RoomSelectionItem(roomDTO.getNumber(), roomDTO.getId())));


        List<GuestDTO> allGuestsAsDto = this.guestService.getGuestsAsDTO();

        List<GuestSelectionItem> guestSelectionItems = new ArrayList<>();

        allGuestsAsDto.forEach(guestDTO -> guestSelectionItems.add(new GuestSelectionItem(guestDTO.getFirstName(), guestDTO.getLastName(), guestDTO.getId())));


        Label roomLabel = new Label("Room: ");
        ComboBox<RoomSelectionItem> roomField = new ComboBox<>();
        roomField.getItems().addAll(roomSelectionItems);

        gridPane.add(roomLabel, 0, 2);
        gridPane.add(roomField, 1, 2);


        Label guestLabel = new Label("Booker: ");
        ComboBox<GuestSelectionItem> guestField = new ComboBox<>();
        guestField.getItems().addAll(guestSelectionItems);

        gridPane.add(guestLabel, 0, 3);
        gridPane.add(guestField, 1, 3);

        Button button = new Button("Create reservation");

        button.setOnAction(actionEvent -> {

            LocalDate from = fromDateField.getValue();
            LocalDate to = toDateField.getValue();
            int roomId = roomField.getValue().getId();
            int guestId = guestField.getValue().getId();


            try {
                this.reservationService.createNewReservation(from, to, roomId, guestId);

                tableView.getItems().clear();
                tableView.getItems().addAll(this.reservationService.getReservationsAsDTO());
                modalStage.close();
            } catch (IllegalArgumentException e) {
                Label error = new Label("Incorrect booking dates");
                error.setTextFill(Color.RED);
                gridPane.add(error, 0, 5);
            }


        });

        gridPane.add(button, 1, 4);

        this.mainScene = new Scene(gridPane, 740, 580);

        this.mainScene.getStylesheets()
                .add(Objects.requireNonNull(getClass()
                                .getClassLoader()
                                .getResource("hotelReservation.css"))
                        .toExternalForm());
    }


    public Scene getMainScene() {
        return this.mainScene;
    }
}
