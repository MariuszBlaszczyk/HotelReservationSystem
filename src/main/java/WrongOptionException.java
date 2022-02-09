public class WrongOptionException extends ReservationCustomerException {

    private final int code = 101;


    public WrongOptionException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }
}
