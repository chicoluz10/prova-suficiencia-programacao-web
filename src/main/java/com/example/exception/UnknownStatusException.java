package com.example.exception;

public class UnknownStatusException extends RuntimeException {

    public UnknownStatusException(String message) {
        super(message);
    }

    public UnknownStatusException(String message, Throwable cause) {
        super(message,cause);
    }
}
