package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM room r WHERE r.room_name = :roomName AND r.house_id = :houseId)",nativeQuery = true)
    Long existsByRoomName(@Param("roomName") String roomName, @Param("houseId") String houseId);
}