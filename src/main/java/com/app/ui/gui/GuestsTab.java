package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuestsTab {

    private final Tab guestTab;


    public GuestsTab(Stage primaryStage) {

        TableView<GuestDTO> tableView = getGuestDTOTableView();


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

        GuestService guestService = ObjectPool.getGuestService();
        tableView.getItems().addAll(guestService.getGuestsAsDTO());

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn);
        return tableView;
    }


    public Tab getGuestTab() {
        return guestTab;
    }
}
