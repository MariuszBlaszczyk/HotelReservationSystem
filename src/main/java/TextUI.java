import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    GuestService guestService = new GuestService();
    RoomService roomService = new RoomService();

    public  void readNewGuestData(Scanner input) {
        System.out.println("We create a new guest.");
        try {
            System.out.print("Please enter a first name: ");
            String firstName = input.next();
            System.out.print("Please enter a last name: ");
            String lastName = input.next();
            System.out.print("Please enter the age: ");
            int age = input.nextInt();
            System.out.println("Please, specify your gender: ");
            Gender.gendersToChooseFrom();
            int gender = input.nextInt();
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, gender);
            System.out.println("New visitors added: " + newGuest);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender.");
        }
    }


    public void readNewRoomData(Scanner input) {
        System.out.println("Creating a new room.");
        try {
            System.out.println("Room number: ");
            int numberRoom = input.nextInt();
            int[] bedType = chooseBedType(input);
            Room newRoom = roomService.createNewRoom(numberRoom, bedType);
            System.out.println("A new room has been created:\n" + newRoom);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room.");
        }
    }

    private int[] chooseBedType(Scanner input) {
        System.out.println("Enter the number of beds in the room");
        int bedNumber = input.nextInt();
        int[] bedTypes = new int[bedNumber];
        for (int i = 0; i < bedTypes.length; i++) {
            BedType.availableBedTypes();
            int bedTypeOption = input.nextInt();
            bedTypes[i] = bedTypeOption;
        }
        return bedTypes;
    }

    public void showSystemInfo(String hotelName, int currentSystemVersion, boolean isDeveloperVersion) {
        System.out.print("Welcome to the reservation system for the ");
        System.out.println(hotelName);
        System.out.print("Current system version: ");
        System.out.println(currentSystemVersion);
        System.out.print("Development version: ");
        System.out.println(isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {
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
            System.out.println("I am leaving the application.");
        }
    }

    private  void performAction(Scanner input) {
        int option = getActionFromUser(input);

        if (option == 1) {
            readNewGuestData(input);
        } else if (option == 2) {
            readNewRoomData(input);
        } else if (option == 3) {
            System.out.println("Selected option 3.");
        } else {
            throw new WrongOptionException("Wrong option in main menu.");
        }
    }


    private static int getActionFromUser(Scanner in) {
        System.out.println("1. Add new guest.");
        System.out.println("2. Add new room.");
        System.out.println("3. Search for a visitor.");
        System.out.println("Select the option: ");
        int option;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu.");
        }
        return option;
    }


}
