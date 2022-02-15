package com.app.exceptions;

public class PersistenceToFileException extends ReservationCustomerException {

    private final int CODE = 103;

    public PersistenceToFileException(String filename, String operation, String message) {
        super(String.format("Unable to perform %s to %s (%s)", operation, filename, message));
    }

    @Override
    public int getCODE() {
        return CODE;
    }
}
