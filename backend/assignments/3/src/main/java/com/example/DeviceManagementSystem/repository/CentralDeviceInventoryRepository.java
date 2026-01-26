package com.example.DeviceManagementSystem.repository;

import com.example.DeviceManagementSystem.entity.CentralDeviceInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CentralDeviceInventoryRepository extends JpaRepository<CentralDeviceInventory, String> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM central_device_inventory cdi WHERE cdi.kickston_id = :kickstonId AND cdi.device_username = :deviceUserName AND cdi.device_password = :devicePassword)",nativeQuery = true)
    Long checkIfGivenDeivceExists(@Param("kickstonId") String kickstonId,@Param("deviceUserName") String deviceUserName,@Param("devicePassword") String devicePassword);
}
