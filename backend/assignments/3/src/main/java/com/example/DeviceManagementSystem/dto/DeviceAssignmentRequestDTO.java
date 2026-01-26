package com.example.DeviceManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceAssignmentRequestDTO {
    private String kickston_id;
    private Integer roomId;
    private String houseId;
    private Long userId;
}
