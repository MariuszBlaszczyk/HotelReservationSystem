import com.app.ui.TextUI;

public class App {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        String hotelName = "Overlook Hotel";
        int currentSystemVersion = 1;
        boolean isDeveloperVersion = true;

        textUI.showSystemInfo(hotelName, currentSystemVersion, isDeveloperVersion);
        textUI.showMainMenu();

    }


}

