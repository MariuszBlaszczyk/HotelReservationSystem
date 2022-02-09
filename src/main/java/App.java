import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static TextUI textUI = new TextUI();

    public static void main(String[] args) {

        String hotelName = "Overlook Hotel";
        int currentSystemVersion = 1;
        boolean isDeveloperVersion = true;

        showSystemInfo(hotelName, currentSystemVersion, isDeveloperVersion);

        Scanner input = new Scanner(System.in);

        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println("An unexpected error has occurred.");
            System.out.println("Error code: " + e.getCode());
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred.");
            System.out.println("Unknown error code.");
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("I exit the application.");
        }
    }

    private static void performAction(Scanner input) {
        int option = getActionFromUser(input);

        if (option == 1) {
            textUI.readNewGuestData(input);
        } else if (option == 2) {
            Room newRoom = createNewRoom(input);
        } else if (option == 3) {
            System.out.println("Selected option 3.");
        } else {
            throw new WrongOptionException("Wrong option in main menu.");
        }
    }


    private static void showSystemInfo(String hotelName, int currentSystemVersion, boolean isDeveloperVersion) {
        System.out.print("Welcome to the reservation system for the ");
        System.out.println(hotelName);
        System.out.print("Current system version: ");
        System.out.println(currentSystemVersion);
        System.out.print("Development version: ");
        System.out.println(isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    private static int getActionFromUser(Scanner in) {
        System.out.println("1. Add new guest.");
        System.out.println("2. Add new room.");
        System.out.println("3. Search for a visitor.");
        System.out.println("Select the option: ");
        int option = 0;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu.");
        }
        return option;
    }

    private static Room createNewRoom(Scanner input) {
        System.out.println("Creating a new room.");
        try {
            System.out.println("Room number: ");
            int numberRoom = input.nextInt();
            BedType[] bedtypes = chooseBedType(input);
            Room newRoom = new Room(numberRoom, bedtypes);
            System.out.println("A new room has been created:\n" + newRoom);
            return newRoom;
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room.");
        }
    }


    static private BedType[] chooseBedType(Scanner input) {
        System.out.println("Enter the number of beds in the room");
        int numberOfBeds = input.nextInt();
        BedType[] bedTypes = new BedType[numberOfBeds];
        for (int i = 0; i < bedTypes.length; i++) {
            BedType.availableBedTypes();
            int bedTypeOption = input.nextInt();
            BedType type = BedType.fromValue(bedTypeOption);
            bedTypes[i] = type;
        }
        return bedTypes;
    }

}

