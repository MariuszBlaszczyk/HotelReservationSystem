package com.app;

import com.app.ui.TextUI;
import com.app.utils.Utils;

public class App {

    private static final TextUI TEXT_UI = new TextUI();

    public static void main(String[] args) {


        Utils.createDataDirectory();
        TEXT_UI.showSystemInfo();
        TEXT_UI.showMainMenu();

    }
}

