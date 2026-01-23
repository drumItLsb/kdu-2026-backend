package com.example.SkySeatFlightBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seats {

    @Id
    @Column(nullable = false)
    private Integer seatNumber;

    @Column(nullable = false)
    private String holdId;

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
