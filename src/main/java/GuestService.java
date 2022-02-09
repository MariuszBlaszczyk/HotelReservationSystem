public class GuestService {


    GuestRepository guestRepository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = Gender.chooseFromNumberValue(genderOption);
        return guestRepository.createNewGuest(firstName, lastName, age, gender);
    }

    public void showAvailableGenders() {
        Gender.gendersToChooseFrom();
    }


}
