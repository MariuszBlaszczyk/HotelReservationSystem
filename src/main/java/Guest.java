import java.util.Objects;

public class Guest {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    public Guest(String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return age == guest.age && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && gender == guest.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, gender);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age: " + age + ", gender: " + gender + ".";
    }

}
