package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HouseRepository extends JpaRepository<House, String> {
}
