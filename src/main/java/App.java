import com.app.ui.TextUI;
import com.app.utils.Properties;

public class App {

    private static final TextUI TEXT_UI = new TextUI();

    public static void main(String[] args) {


        Properties.createDataDirectory();
        TEXT_UI.showSystemInfo();
        TEXT_UI.showMainMenu();

    }
}

