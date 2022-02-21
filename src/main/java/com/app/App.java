package com.app;

import com.app.ui.gui.PrimaryStage;
import com.app.ui.text.TextUI;
import com.app.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    private static final TextUI TEXT_UI = new TextUI();

    public static void main(String[] args) {


        Utils.createDataDirectory();

        Application.launch(args);

    }

    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);
    }
}

