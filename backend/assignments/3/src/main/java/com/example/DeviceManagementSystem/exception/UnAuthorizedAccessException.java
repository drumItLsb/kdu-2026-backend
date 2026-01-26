package com.example.DeviceManagementSystem.exception;

public class UnAuthorizedAccessException extends RuntimeException {
    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}