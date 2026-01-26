package com.example.DeviceManagementSystem.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceAssignmentToHouseDTO {
    @NotBlank
    private String kickston_id;
    @NotBlank
    private String houseId;
    private Long userId;
    @NotBlank
    private String device_password;
    @NotBlank
    private String device_username;
}
