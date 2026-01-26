package com.example.DeviceManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE CentralDeviceInventory SET is_deleted = true, deleted_date = NOW() WHERE kickston_id = ?")
@SQLRestriction("is_deleted = false")
public class CentralDeviceInventory extends BaseEntity {
    @Id
    @Column(length = 6, columnDefinition = "CHAR(6)")
    @Pattern(regexp = "^[0-9A-F]{6}$", message = "kickston_id must be a 6-character uppercase hexadecimal string")
    private String kickston_id;

    @Column(nullable = false)
    @NotBlank(message = "device_username is required")
    private String device_username;

    @Column(nullable = false)
    @NotBlank(message = "device_password is required")
    private String device_password;

    @Column(nullable = false)
    @NotNull(message = "manufacture_date_time is required")
    private LocalDateTime manufacture_date_time;

    @Column(nullable = false)
    @NotNull(message = "manufacture_factory_place is required")
    private String manufacture_factory_place;
}
