package com.app.ui.gui.guests;

import com.app.domain.ObjectPool;
import com.app.domain.guest.GuestService;
import com.app.domain.guest.dto.GuestDTO;
import com.app.utils.SystemUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class AddNewGuestScene {

    private final Scene mainScene;
    private final GuestService guestService = ObjectPool.getGuestService();

    public AddNewGuestScene(Stage modalStage, TableView<GuestDTO> guestTableView) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);

        Label firstNameLabel = new Label("First name:");
        TextField firstNameField = new TextField();

        firstNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                firstNameField.setText(oldValue);
            }
        });

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);

        Label lastNameLabel = new Label("Last name:");
        TextField lastNameField = new TextField();

        lastNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                lastNameField.setText(oldValue);
            }
        });

        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);

        Label ageLabel = new Label("Age: ");
        TextField ageTextField = new TextField();

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
        genderField.setValue(SystemUtils.FEMALE);

        gridPane.add(genderLabel, 0, 3);
        gridPane.add(genderField, 1, 3);

        Button createNewGuestButton = new Button("Create guest");
        createNewGuestButton.setOnAction(actionEvent -> {

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String gender = genderField.getValue();

            boolean isMale = gender.equals(SystemUtils.MALE);

            this.guestService.createNewGuest(firstName, lastName, age, isMale);

            guestTableView.getItems().clear();
            guestTableView.getItems().addAll(this.guestService.getGuestsAsDTO());

            modalStage.close();

        });

        gridPane.add(createNewGuestButton, 1, 4);

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
