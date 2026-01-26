package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface HouseRepository extends JpaRepository<House, String> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM house WHERE house_name = :houseName AND owner_id = :ownerId)",nativeQuery = true)
    Long existsByHouseName(@Param("houseName") String houseName, @Param("ownerId") Long ownerId);
}
