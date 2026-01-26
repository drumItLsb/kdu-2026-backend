package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.DeviceAssignment;
import com.example.DeviceManagementSystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DeviceAssignmentRepository extends JpaRepository<DeviceAssignment, String> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM device_assignment d WHERE d.kickston_id = :kickstonId AND d.house_id = :houseId)",nativeQuery = true)
    Long checkIfDeviceExistsInRoom(@Param("kickstonId") String kickstonId, @Param("houseId") String houseId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM device_assignment d WHERE d.kickston_id = :kickstonId AND d.house_id != :houseId)",nativeQuery = true)
    Long checkIfDeviceExistsInDifferentHouse(@Param("kickstonId") String kickstonId, @Param("houseId") String houseId);

    @Modifying
    @Query(value = "UPDATE device_assignment d SET d.room_id = :roomId WHERE d.kickston_id = :kickstonId AND d.house_id = :houseId",nativeQuery = true)
    Long changeDeviceRoom(@Param("kickstonId") String kickstonId, @Param("houseId") String houseId, @Param("roomId") Integer roomId);

    @Query(value = "SELECT d.kickston_id FROM device_assignment d WHERE d.house_id = :houseId", nativeQuery = true)
    List<String> getAllDevicesInHouse(@Param("houseId") String houseId);
}