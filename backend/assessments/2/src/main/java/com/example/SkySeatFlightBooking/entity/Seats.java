package com.example.SkySeatFlightBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seats {

    @Id
    private String holdId;

    @PrePersist
    public void generateId() {
        if (this.holdId == null) {
            this.holdId = UUID.randomUUID().toString();
        }
    }

    @Column(nullable = false)
    private Integer seatNumber;

    @Column(nullable = false)
    private boolean hold = true;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    @Column(nullable = false)
    private boolean booked = false;

    @Column(nullable = false)
    private boolean cancelled = false;
}
