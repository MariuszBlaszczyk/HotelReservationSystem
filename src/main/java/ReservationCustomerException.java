abstract public class ReservationCustomerException extends RuntimeException {

    abstract int getCode();

    public ReservationCustomerException(String message) {
        super(message);
    }
}
