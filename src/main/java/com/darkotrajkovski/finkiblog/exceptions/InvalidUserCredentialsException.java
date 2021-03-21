package com.darkotrajkovski.finkiblog.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Invalid user");
    }
}
