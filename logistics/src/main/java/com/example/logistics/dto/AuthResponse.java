package com.example.logistics.dto;

import java.util.List;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private String userName;
    private String roles;

    public AuthResponse(String token, String userName, String roles) {
        this.token = token;
        this.userName = userName;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public String getUserName() { return userName; }
    public String getRoles() { return roles; }
}