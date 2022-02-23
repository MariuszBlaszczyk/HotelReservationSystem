package com.app.ui.gui;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GuestsTab {

    private final Tab guestTab;


    public GuestsTab() {

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

        this.guestTab = new Tab("Guests", tableView);
        guestTab.setClosable(false);
    }


    public Tab getGuestTab() {
        return guestTab;
    }
}
