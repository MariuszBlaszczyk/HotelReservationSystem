package com.app.ui.gui;

import com.app.utils.Utils;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryStage {

    public void initialize(Stage primaryStage) {
        String hotelName = Utils.HOTEL_NAME;
        int systemVersion = Utils.SYSTEM_VERSION;

        MainTabView mainTabView = new MainTabView();

        Scene scene = new Scene(mainTabView.getMainTabs(), 640, 480);
        String title = String.format("Hotel reservation system %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
