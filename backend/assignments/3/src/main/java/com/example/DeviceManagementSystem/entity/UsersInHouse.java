package com.example.DeviceManagementSystem.entity;

import com.example.DeviceManagementSystem.entity.compositeKeys.UsersInHouseId;
import jakarta.persistence.*;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UsersInHouseId.class)
@SQLDelete(sql = "UPDATE UsersInHouse SET is_deleted = true, deleted_date = NOW() WHERE house_id = ? AND user_id = ?")
@SQLRestriction("is_deleted = false")
public class UsersInHouse extends BaseEntity {

    @Id
    @OneToOne
    @JoinColumn(name="house_id",nullable = false)
    private House house_id;

    @Id
    @OneToOne
    @JoinColumn(name="user_id",nullable = false, referencedColumnName = "id", columnDefinition = "BIGINT")
    private User user_id;

    @Column(nullable = false)
    private boolean is_admin;
}
