import java.util.Scanner;

public class GuestService {

    GuestRepository guestRepository =  new GuestRepository();

    public  Guest createNewGuest(Scanner input) {
        return guestRepository.createNewGuest(input);
    }
}
