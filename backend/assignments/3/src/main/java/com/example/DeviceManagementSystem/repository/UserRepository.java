package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
}