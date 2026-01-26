package com.example.DeviceManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public LocalDateTime created_date;

    @UpdateTimestamp
    @Column(nullable = false)
    public LocalDateTime modified_date;

    public LocalDateTime deleted_date;

    @Column(nullable = false)
    public boolean is_deleted = false;
}
