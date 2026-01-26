package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.DeviceAssignment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeviceAssignmentRepository extends JpaRepository<DeviceAssignment, String> {
}