package com.example.talentPortal.dto;

import java.util.List;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private String userName;
    private List<String> roles;

    public AuthResponse(String token, String userName, List<String> roles) {
        this.token = token;
        this.userName = userName;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public String getUserName() { return userName; }
    public List<String> getRoles() { return roles; }
}
