package com.example.DeviceManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DevicesInHouseRequestDTO {
    @NotBlank
    private String houseId;
    private Long userId;
}
