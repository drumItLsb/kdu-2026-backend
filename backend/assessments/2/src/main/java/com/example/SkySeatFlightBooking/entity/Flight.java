package com.example.SkySeatFlightBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer flightNumber;

    @Column(nullable = false)
    @NotBlank(message = "source is required")
    private String source;

    @Column(nullable = false)
    @NotBlank(message = "destination is required")
    private String destination;

    @Column(nullable = false)
    @NotBlank(message = "DepartureTime is required")
    private LocalDateTime departureTime;

    @Column(nullable = false)
    @NotBlank(message = "totalSeats is required")
    private Integer totalSeats;
}
