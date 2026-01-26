package com.example.DeviceManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HouseCreationResponseDTO {
    private String house_id;
    private String house_name;
    private String address;
    private String owner_name;
}
