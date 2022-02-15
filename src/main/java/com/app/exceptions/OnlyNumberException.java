package com.app.exceptions;

public class OnlyNumberException extends ReservationCustomerException {

    private final int CODE = 102;


    public OnlyNumberException(String message) {
        super(message);
    }

    public int getCODE() {
        return CODE;
    }
}
