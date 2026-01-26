package com.example.DeviceManagementSystem.service;

import com.example.DeviceManagementSystem.dto.DeviceInventoryResponseDTO;
import com.example.DeviceManagementSystem.entity.CentralDeviceInventory;
import com.example.DeviceManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.DeviceManagementSystem.repository.CentralDeviceInventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CentralDeviceInventoryService {
    private final CentralDeviceInventoryRepository centralDeviceInventoryRepository;

    public CentralDeviceInventoryService(CentralDeviceInventoryRepository centralDeviceInventoryRepository) {
        this.centralDeviceInventoryRepository = centralDeviceInventoryRepository;
    }

    public DeviceInventoryResponseDTO registerDevice(CentralDeviceInventory centralDeviceInventoryDevice) {
        if(centralDeviceInventoryRepository.existsById(centralDeviceInventoryDevice.getKickston_id())) {
            throw new ResourceAlreadyExistsException("Device already exists");
        }

        CentralDeviceInventory addedDevice = centralDeviceInventoryRepository.save(centralDeviceInventoryDevice);
        return new DeviceInventoryResponseDTO(addedDevice.getKickston_id(),addedDevice.getDevice_username());
    }
}
