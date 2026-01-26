package com.example.DeviceManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class DevicesInHouseResponseDTO {
    List<String> devicesList;
}
