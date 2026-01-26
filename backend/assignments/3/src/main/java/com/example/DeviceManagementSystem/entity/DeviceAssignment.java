package com.example.DeviceManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE DeviceAssignment SET is_deleted = true, deleted_date = NOW() WHERE kickston_id = ?")
@SQLRestriction("is_deleted = false")
public class DeviceAssignment extends BaseEntity{

    @Id
    private String id; // this will get value from centalDeviceInventory's kickston_id field and id gets renamed to kickston_id

    @OneToOne
    @MapsId
    @JoinColumn(name = "kickston_id",nullable = false)
    private CentralDeviceInventory kickston_id;

    @OneToOne
    @JoinColumn(name = "house_id",nullable = false)
    private House house_id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room_id;

    public DeviceAssignment(CentralDeviceInventory kickston_id, House house_id, Room room_id) {
        this.kickston_id = kickston_id;
        this.house_id = house_id;
        this.room_id = room_id;
    }
}
