package com.example.DeviceManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SwitchAdminRequestDTO {
    private Long currentAdminId;
    private Long newAdminId;
    @NotBlank
    private String houseId;
}
