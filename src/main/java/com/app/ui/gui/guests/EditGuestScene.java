package com.app.ui.gui.guests;

import com.app.domain.ObjectPool;
import com.app.domain.guest.Gender;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import com.app.utils.SystemUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class EditGuestScene {
    private final Scene mainScene;
    private final GuestService guestService = ObjectPool.getGuestService();

    public EditGuestScene(Stage modalStage, TableView<GuestDTO> tableView, GuestDTO guestDTO) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);

        Label firstNameLabel = new Label("First name:");
        TextField firstNameField = new TextField();
        firstNameField.setText(guestDTO.getFirstName());

        firstNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                firstNameField.setText(oldValue);
            }
        });

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);

        Label lastNameLabel = new Label("Last name:");
        TextField lastNameField = new TextField();
        lastNameField.setText(guestDTO.getLastName());

        lastNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                lastNameField.setText(oldValue);
            }
        });

        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);

        Label ageLabel = new Label("Age: ");
        TextField ageTextField = new TextField();
        ageTextField.setText(String.valueOf(guestDTO.getAge()));

        ageLabel.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageTextField.setText(oldValue);
            }
        });

        gridPane.add(ageLabel, 0, 2);
        gridPane.add(ageTextField, 1, 2);

        Label genderLabel = new Label("Gender: ");
        ComboBox<String> genderField = new ComboBox<>();
        genderField.getItems().addAll(SystemUtils.FEMALE, SystemUtils.MALE);
        genderField.setValue(guestDTO.getGender());

        gridPane.add(genderLabel, 0, 3);
        gridPane.add(genderField, 1, 3);

        Button editGuestButton = new Button("Edit guest");
        editGuestButton.setOnAction(actionEvent -> {

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            int male = Gender.MALE.getValue();


            this.guestService.editGuestFromList(guestDTO.getId(), firstName, lastName, age, male);

            tableView.getItems().clear();
            tableView.getItems().addAll(this.guestService.getGuestsAsDTO());

            modalStage.close();

        });

        gridPane.add(editGuestButton, 1, 4);

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
