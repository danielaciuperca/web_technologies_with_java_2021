package com.unibuc.ex1curs14.exception;

public class DriverAlreadyExistsException extends RuntimeException {

    public DriverAlreadyExistsException() {
        super("There is already a driver with the same email.");
    }
}
