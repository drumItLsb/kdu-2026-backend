package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.UsersInHouse;
import com.example.DeviceManagementSystem.entity.compositeKeys.UsersInHouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsersInHouseRepository extends JpaRepository<UsersInHouse, UsersInHouseId> {
    @Query(value = "SELECT u.is_admin FROM users_in_house u WHERE u.user_id = :userId AND u.house_id = :houseId",nativeQuery = true)
    Boolean checkIsAdmin(@Param("userId") Long userId, @Param("houseId") String houseId);
}