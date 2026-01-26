package com.example.DeviceManagementSystem.controller;

import com.example.DeviceManagementSystem.dto.DeviceInventoryResponseDTO;
import com.example.DeviceManagementSystem.entity.CentralDeviceInventory;
import com.example.DeviceManagementSystem.service.CentralDeviceInventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CentralDeviceInventoryController {
    private CentralDeviceInventoryService centralDeviceInventoryService;

    public CentralDeviceInventoryController(CentralDeviceInventoryService centralDeviceInventoryService) {
        this.centralDeviceInventoryService = centralDeviceInventoryService;
    }

    @PostMapping("/devices")
    public ResponseEntity<DeviceInventoryResponseDTO> registerDevice(@Valid @RequestBody CentralDeviceInventory centralDeviceInventoryDevice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centralDeviceInventoryService.registerDevice(centralDeviceInventoryDevice));
    }
}
