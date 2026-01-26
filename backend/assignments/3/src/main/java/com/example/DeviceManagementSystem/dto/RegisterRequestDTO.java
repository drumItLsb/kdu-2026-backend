package com.example.DeviceManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank
    @Size(min = 3, max = 60)
    private String userName;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    @Email
    private String email;
}
