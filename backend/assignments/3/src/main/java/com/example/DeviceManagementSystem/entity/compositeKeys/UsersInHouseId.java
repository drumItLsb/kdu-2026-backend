package com.example.DeviceManagementSystem.entity.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersInHouseId implements Serializable { // for defining composite key
    private String house_id; // these name should match with the ones in UsersInHouse
    private Long user_id;
}