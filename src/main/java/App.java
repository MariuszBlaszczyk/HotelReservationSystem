import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String hotelName = "Overlook Hotel";
        int currentSystemVersion = 1;
        boolean isDeveloperVersion = true;

        System.out.print("Welcome to the reservation system for the ");
        System.out.println(hotelName);
        System.out.print("Current system version: ");
        System.out.println(currentSystemVersion);
        System.out.print("Development version: ");
        System.out.println(isDeveloperVersion);

        System.out.println("\n=========================\n");

        System.out.println("1. Add new guest.");
        System.out.println("2. Add new room.");
        System.out.println("3. Search for a guest.");
        System.out.println("Select the option: ");

        int option = 0;
        try (Scanner input = new Scanner(System.in)) {
            option = input.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect data has been entered, please enter a number.");
        }


        if (option == 1) {
            System.out.println("Creating a new guest");
            Guest createdGuest = new Guest();
        } else if (option == 2) {
            System.out.println("Selected option 2.");
        } else if (option == 3) {
            System.out.println("Selected option 3.");
        } else {
            System.out.println("Incorrect data entered, please enter a number between 1 and 3");
        }


    }
}

