package com.example.logistics.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Package {

    @Id
    private String id;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String deliveryType;

    public Package() {}

    public Package(String id, String destination,double weight, String status, String deliveryType) {
        this.id = id;
        this.destination = destination;
        this.weight = weight;
        this.status = status;
        this.deliveryType=  deliveryType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

