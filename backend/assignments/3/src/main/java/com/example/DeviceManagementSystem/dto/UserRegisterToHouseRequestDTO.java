package com.example.DeviceManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterToHouseRequestDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String houseId;

    private Long userToRegisterId;
}
