package com.app.exceptions;

public class WrongOptionException extends ReservationCustomerException {

    private final int CODE = 101;


    public WrongOptionException(String message) {
        super(message);
    }

    public int getCODE() {
        return CODE;
    }
}
