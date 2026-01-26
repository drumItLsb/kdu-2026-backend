package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.CentralDeviceInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentralDeviceInventoryRepository extends JpaRepository<CentralDeviceInventory, String> {
}
