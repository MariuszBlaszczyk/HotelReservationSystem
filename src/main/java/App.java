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
        Scanner input = new Scanner(System.in);
        System.out.println("1. Add new guest.");
        System.out.println("2. Add new room.");
        System.out.println("3. Search for a guest.");
        System.out.println("Select the option: ");

        int option = input.nextInt();

        if (option == 1) {
            System.out.println("Selected option 1.");
        } else if (option == 2) {
            System.out.println("Selected option 2.");
        } else if (option == 3) {
            System.out.println("Selected option 3.");
        } else {
            System.out.println("Incorrect choice");
        }


    }
}

