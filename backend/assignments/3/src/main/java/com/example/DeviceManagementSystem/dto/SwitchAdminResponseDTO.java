package com.example.DeviceManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SwitchAdminResponseDTO {
    private Long currentAdminId;
    private Long newAdminId;
    private String houseId;
}
