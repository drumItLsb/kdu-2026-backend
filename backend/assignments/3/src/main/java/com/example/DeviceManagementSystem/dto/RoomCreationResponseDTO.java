package com.example.DeviceManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RoomCreationResponseDTO {
    private String roomName;
    private Integer roomId;
    private String houseName;
    private String userName;
}
