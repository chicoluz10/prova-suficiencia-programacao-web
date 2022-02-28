package com.example.exception;

public class MissingRegisterException extends RuntimeException{
    public MissingRegisterException(String message) {
        super(message);
    }

    public MissingRegisterException(String message, Throwable cause) {
        super(message,cause);
    }
}
