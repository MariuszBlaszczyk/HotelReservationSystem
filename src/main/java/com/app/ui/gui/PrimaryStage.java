package com.app.ui.gui;

import com.app.utils.SystemUtils;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class PrimaryStage {

    public void initialize(Stage primaryStage) {
        String hotelName = SystemUtils.HOTEL_NAME;
        String systemVersion = SystemUtils.SYSTEM_VERSION;

        MainTabView mainTabView = new MainTabView(primaryStage);

        Scene scene = new Scene(mainTabView.getMainTabs(), 1300, 700);

        scene
                .getStylesheets()
                .add(Objects.requireNonNull(getClass()
                                .getClassLoader()
                                .getResource("hotelReservation.css"))
                        .toExternalForm());
        String title = String.format("Hotel reservation system %s (%s)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
