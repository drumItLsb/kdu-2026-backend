package com.example.DeviceManagementSystem.controller;


import com.example.DeviceManagementSystem.dto.*;
import com.example.DeviceManagementSystem.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
