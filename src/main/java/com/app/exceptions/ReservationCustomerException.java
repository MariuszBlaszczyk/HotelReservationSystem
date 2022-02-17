package com.app.exceptions;

abstract public class ReservationCustomerException extends RuntimeException {

    public abstract int getCode();

    public ReservationCustomerException(String message) {
        super(message);
    }
}
