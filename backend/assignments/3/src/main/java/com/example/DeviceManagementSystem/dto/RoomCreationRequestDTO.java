package com.example.DeviceManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
public class RoomCreationRequestDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String roomName;
    @NotBlank
    private String houseId;
}
