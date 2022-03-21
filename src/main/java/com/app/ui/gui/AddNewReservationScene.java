package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import com.app.domain.reservation.dto.ReservationDTO;
import com.app.domain.room.RoomService;
import com.app.domain.room.dto.RoomDTO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewReservationScene {

    private final Scene mainScene;
    private final RoomService roomService = ObjectPool.getRoomService();//potrzebujemy roomService
    private final GuestService guestService = ObjectPool.getGuestService();//potrzebujemy guestService

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


        List<RoomDTO> allRoomsAsDTO = this.roomService.getAllAsDTO();//tworzymy zmienną do przesyłania danych

        List<RoomSelectionItem> roomSelectionItems = new ArrayList<>();//tworzymy listę, w której będziemy tworzyć pokoje z numerem i id

        allRoomsAsDTO.forEach(roomDTO -> {
            roomSelectionItems.add(new RoomSelectionItem(roomDTO.getNumber(), roomDTO.getId()));
        });//iterujemy po allAsDto i wyciągamy potrzebne dane, które przypisujemy do nowej listy

        //dla gości piszemy kod na tej samej zasadzie co dla pokoi
        List<GuestDTO> allGuestsAsDto = this.guestService.getGuestsAsDTO();

        List<GuestSelectionItem> guestSelectionItems = new ArrayList<>();

        allGuestsAsDto.forEach(guestDTO -> {
            guestSelectionItems.add(new GuestSelectionItem(guestDTO.getFirstName(), guestDTO.getLastName(), guestDTO.getId()));
        });


        Label roomLabel = new Label("Room: ");//tworzymy label
        ComboBox<RoomSelectionItem> roomField = new ComboBox<>();//tworzymy combobox dla wyboru danych o pokoju
        roomField.getItems().addAll(roomSelectionItems);//dodajemy listę pokoi

        gridPane.add(roomLabel, 0, 2);//dodajemy do gridPane
        gridPane.add(roomField, 1, 2);//dodajmy do gridPane

        //dla gościa piszemy kod na podobnej zasadzie jak dla pokoju
        Label guestLabel = new Label("Booker: ");
        ComboBox<GuestSelectionItem> guestField = new ComboBox<>();
        guestField.getItems().addAll(guestSelectionItems);

        gridPane.add(guestLabel, 0, 3);
        gridPane.add(guestField, 1, 3);


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
