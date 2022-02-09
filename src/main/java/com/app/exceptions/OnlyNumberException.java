package com.app.exceptions;

public class OnlyNumberException extends ReservationCustomerException {

    private final int code = 102;


    public OnlyNumberException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }
}
