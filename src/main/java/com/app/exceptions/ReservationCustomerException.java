package com.app.exceptions;

abstract public class ReservationCustomerException extends RuntimeException {

    public abstract int getCODE();

    public ReservationCustomerException(String message) {
        super(message);
    }
}
