package com.example.DeviceManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
