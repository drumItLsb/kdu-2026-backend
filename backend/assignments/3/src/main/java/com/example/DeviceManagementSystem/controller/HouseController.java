package com.example.DeviceManagementSystem.controller;


import com.example.DeviceManagementSystem.dto.*;
import com.example.DeviceManagementSystem.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HouseController {

    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/houses")
    public ResponseEntity<HouseCreationResponseDTO> createHouse(@Valid @RequestBody HouseCreationRequestDTO houseCreationRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(houseService.createHouse(houseCreationRequestDTO));
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomCreationResponseDTO> createRoom(@Valid @RequestBody RoomCreationRequestDTO roomCreationRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(houseService.createRoom(roomCreationRequestDTO));
    }

    @PostMapping("/register/house/user")
    public ResponseEntity<UserRegisterToHouseResponseDTO> registerUserToHouse(@Valid @RequestBody UserRegisterToHouseRequestDTO userRegisterToHouseRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(houseService.registerUserToHouse(userRegisterToHouseRequestDTO));
    }

    @PostMapping("/register/house/device")
    public ResponseEntity<DeviceAssignmentToHouseResponseDTO> assignDeviceToHouse(@Valid @RequestBody DeviceAssignmentToHouseDTO deviceAssignmentToHouseDTO) {
        return ResponseEntity.ok(houseService.assignDeviceToHouse(deviceAssignmentToHouseDTO));
    }

    @PostMapping("/register/room/device")
    public ResponseEntity<DeviceAssignmentResponseDTO> assignDeviceToRoom(@Valid @RequestBody DeviceAssignmentRequestDTO deviceAssignmentRequestDTO) {
        return ResponseEntity.ok(houseService.assignDeviceToRooms(deviceAssignmentRequestDTO));
    }

    @PostMapping("/house/devices")
    public ResponseEntity<DevicesInHouseResponseDTO> getAllDevicesFromHouse(@Valid @RequestBody DevicesInHouseRequestDTO devicesInHouseRequestDTO) {
        return ResponseEntity.ok(houseService.getAllDevicesFromHouse(devicesInHouseRequestDTO));
    }

    @GetMapping("/house/{userId}")
    public ResponseEntity<List<String>> getAllHousesOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(houseService.getAllHousesOfUser(userId));
    }

    @PatchMapping("/house/admins")
    public ResponseEntity<SwitchAdminResponseDTO> switchAdmins(@Valid @RequestBody SwitchAdminRequestDTO switchAdminRequestDTO) {
        return ResponseEntity.ok(houseService.changeAdmins(switchAdminRequestDTO));
    }
}
