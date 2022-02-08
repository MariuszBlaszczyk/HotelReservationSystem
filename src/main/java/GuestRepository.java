import java.util.InputMismatchException;
import java.util.Scanner;

public class GuestRepository {

    public GuestRepository() {
    }

    public Guest createNewGuest(Scanner input) {
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
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender.");
        }
    }

    private static Gender chooseYourGender(Scanner input) {
        Gender.chooseYourGender();
        int genderOption = input.nextInt();
        return Gender.fromValue(genderOption);
    }

}
