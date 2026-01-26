package com.example.DeviceManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceAssignmentResponseDTO {
    private String kickston_id;
    private Integer roomId;
    private String houseId;
}
