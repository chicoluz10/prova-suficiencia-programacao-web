package com.example.exception;

public class MissingUsernameException extends RuntimeException {

    public MissingUsernameException(String message) {
        super(message);
    }

    public MissingUsernameException(String message, Throwable cause) {
        super(message,cause);
    }
}
