package com.example.exception;

public class UnavailableStockException extends RuntimeException {
    public UnavailableStockException(String message) {
        super(message);
    }

    public UnavailableStockException(String message, Throwable cause) {
        super(message,cause);
    }
}
