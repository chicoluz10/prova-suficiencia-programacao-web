package com.example.exception;

public class NoActiveMembershipException extends RuntimeException {

    public NoActiveMembershipException(String message) {
        super(message);
    }

    public NoActiveMembershipException(String message, Throwable cause) {
        super(message,cause);
    }
}
