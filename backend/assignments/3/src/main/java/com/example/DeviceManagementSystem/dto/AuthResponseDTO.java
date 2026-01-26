package com.example.DeviceManagementSystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthResponseDTO {
    private final String token;
    private final String tokenType = "Bearer";
    private final String userName;

    public AuthResponseDTO(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }
}