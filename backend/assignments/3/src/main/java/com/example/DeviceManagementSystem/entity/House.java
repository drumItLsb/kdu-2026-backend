package com.example.DeviceManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE House SET is_deleted = true, deleted_date = NOW() WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class House extends BaseEntity {
    @Id
    private String id;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "house_name is required")
    private String house_name;

    @OneToMany(mappedBy = "house_id")
    private List<Room> rooms;
}
