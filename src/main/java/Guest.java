import java.util.Objects;
import java.util.Scanner;

public class Guest {

    String firstName;
    String lastName;
    int age;

    public Guest(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


//
//    public Guest addNewGuest(String firstName, String lastName, int age) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            firstName = scanner.next();
//            lastName = scanner.next();
//            age = scanner.nextInt();
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return new Guest(firstName,lastName,age);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return age == guest.age && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age: " + age + ".";
    }

}
