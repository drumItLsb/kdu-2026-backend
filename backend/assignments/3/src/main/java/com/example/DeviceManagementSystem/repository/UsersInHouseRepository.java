package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.UsersInHouse;
import com.example.DeviceManagementSystem.entity.compositeKeys.UsersInHouseId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersInHouseRepository extends JpaRepository<UsersInHouse, UsersInHouseId> {
}