import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String hotelName = "Overlook Hotel";
        int currentSystemVersion = 2;
        boolean isDeveloperVersion = true;

        showSystemInfo(hotelName, currentSystemVersion, isDeveloperVersion);

        Scanner input = new Scanner(System.in);

        int option = getActionFromUser(input);

        if (option == 1) {
            Guest newGuest = createNewGuest(input);
        } else if (option == 2) {
            Room newRoom = createNewRoom(input);
        } else if (option == 3) {
            System.out.println("Selected option 3.");
        } else {
            System.out.println("Incorrect data entered, please enter a number between 1 and 3");
        }


    }

    private static Room createNewRoom(Scanner input) {
        System.out.println("Creating a new room.");
        try {
            System.out.println("Room number: ");
            int numberRoom = input.nextInt();
            BedType bedtype = chooseBedType(input);
            Room newRoom = new Room(numberRoom, bedtype);
            System.out.println("A new room has been created:\n" + newRoom);
            return newRoom;
        } catch (Exception e) {
            System.out.println("It was not possible to create a new room. The number and number of beds must be numbers.");
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            System.out.println("Incorrect input, enter number.");
            e.printStackTrace();
        }
        return option;
    }

    private static Guest createNewGuest(Scanner input) {
        System.out.println("We create a new guest.");
        try {
            System.out.print("Please enter a first name: ");
            String firstName = input.next();
            System.out.print("Please enter a last name: ");
            String lastName = input.next();
            System.out.print("Please enter the age: ");
            int age = input.nextInt();
            System.out.println("Please, specify your gender: ");
            Gender gender = chooseYourGender(input);
            Guest newGuest = new Guest(firstName, lastName, age, gender);
            System.out.println("New visitors added: " + newGuest);
            return newGuest;
        } catch (Exception e) {
            System.out.println("Wrong age, use numbers.");
            e.printStackTrace();
            return null;
        }
    }

    private static Gender chooseYourGender(Scanner input) {
        Gender.chooseYourGender();
        int genderOption = input.nextInt();
        Gender gender = Gender.fromValue(genderOption);
        return gender;
    }

    static private BedType chooseBedType(Scanner input) {
        BedType.availableBedTypes();
        int bedTypeOption = input.nextInt();
        return BedType.fromValue(bedTypeOption);
    }

}

