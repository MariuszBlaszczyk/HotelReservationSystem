public class OnlyNumberException extends ReservationCustomerException {

    private int code = 102;


    public OnlyNumberException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }
}
