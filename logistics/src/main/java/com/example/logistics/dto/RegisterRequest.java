package com.example.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 60)
    private String userName;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    private String role;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getRoles() { return role; }
    public void setRoles(String roles) { this.role = role; }
}
