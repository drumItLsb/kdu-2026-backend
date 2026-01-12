package com.example.library.exceptions;

public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    // Constructors, Getters, and Setters
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
