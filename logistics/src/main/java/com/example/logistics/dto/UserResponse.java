package com.example.logistics.dto;

public class UserResponse {
    private String userName;
    private String role;

    public UserResponse(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() { return userName; }
    public String getRoles() { return role; }
}