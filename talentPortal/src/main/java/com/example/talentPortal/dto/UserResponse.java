package com.example.talentPortal.dto;

import java.util.List;

public class UserResponse {
    private String userName;
    private String email;
    private List<String> roles;

    public UserResponse(String userName, String email, List<String> roles) {
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public List<String> getRoles() { return roles; }
}
