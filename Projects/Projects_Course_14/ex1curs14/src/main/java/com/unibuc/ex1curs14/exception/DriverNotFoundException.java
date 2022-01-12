package com.unibuc.ex1curs14.exception;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException() {
        super("The driver doesn't exist.");
    }
}
