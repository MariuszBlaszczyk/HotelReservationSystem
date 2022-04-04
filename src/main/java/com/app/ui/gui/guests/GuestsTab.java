package com.app.ui.gui.guests;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuestsTab {

    private final Tab guestTab;
    private final GuestService guestService = ObjectPool.getGuestService();
    private final Stage primaryStage;


    public GuestsTab(Stage primaryStage) {

        TableView<GuestDTO> tableView = getGuestDTOTableView();
        this.primaryStage = primaryStage;

        Button button = new Button("Add new guest");

        button.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);


            stage.setScene(new AddNewGuestScene(stage, tableView).getMainScene());

            stage.initOwner(primaryStage);
            stage.setTitle("Add new guest");
            stage.showAndWait();
        });

        VBox layout = new VBox(button, tableView);


        this.guestTab = new Tab("Guests", layout);
        guestTab.setClosable(false);
    }

    private TableView<GuestDTO> getGuestDTOTableView() {
        TableView<GuestDTO> tableView = new TableView<>();

        TableColumn<GuestDTO, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<GuestDTO, String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<GuestDTO, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<GuestDTO, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<GuestDTO, GuestDTO> actionColumn = new TableColumn<>();
        actionColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper<>(value.getValue()));


        actionColumn.setCellFactory(param -> new TableCell<>() {

            final Button deleteButton = new Button("Delete");
            final Button editButton = new Button("Edit");
            final HBox hBox = new HBox(deleteButton, editButton);

            @Override
            protected void updateItem(GuestDTO value, boolean empty) {
                super.updateItem(value, empty);

                if (value == null) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                    deleteButton.setOnAction(actionEvent -> {
                        guestService.removeGuestFromList(value.getId());
                        tableView.getItems().remove(value);
                    });
                    editButton.setOnAction(actionEvent -> {
                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.setScene(new EditGuestScene(stage, tableView, value).getMainScene());
                        stage.initOwner(primaryStage);
                        stage.setTitle("Edit guest");
                        stage.showAndWait();
                    });
                }
            }
        });


        tableView.getItems().addAll(guestService.getGuestsAsDTO());

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn, actionColumn);
        return tableView;
    }


    public Tab getGuestTab() {
        return guestTab;
    }
}
