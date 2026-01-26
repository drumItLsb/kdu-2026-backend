package com.example.DeviceManagementSystem.service;

import com.example.DeviceManagementSystem.dto.DeviceInventoryResponseDTO;
import com.example.DeviceManagementSystem.entity.CentralDeviceInventory;
import com.example.DeviceManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.DeviceManagementSystem.repository.CentralDeviceInventoryRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CentralDeviceInventoryService {
    private final CentralDeviceInventoryRepository centralDeviceInventoryRepository;
    private final PasswordEncoder passwordEncoder;

    public CentralDeviceInventoryService(CentralDeviceInventoryRepository centralDeviceInventoryRepository, PasswordEncoder passwordEncoder) {
        this.centralDeviceInventoryRepository = centralDeviceInventoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public DeviceInventoryResponseDTO registerDevice(CentralDeviceInventory centralDeviceInventoryDevice) {
        if(centralDeviceInventoryRepository.existsById(centralDeviceInventoryDevice.getKickston_id())) {
            throw new ResourceAlreadyExistsException("Device already exists");
        }

        String hashedPassword = passwordEncoder.encode(centralDeviceInventoryDevice.getDevice_password());
        centralDeviceInventoryDevice.setDevice_password(hashedPassword);
        CentralDeviceInventory addedDevice = centralDeviceInventoryRepository.save(centralDeviceInventoryDevice);
        return new DeviceInventoryResponseDTO(addedDevice.getKickston_id(),addedDevice.getDevice_username());
    }
}
