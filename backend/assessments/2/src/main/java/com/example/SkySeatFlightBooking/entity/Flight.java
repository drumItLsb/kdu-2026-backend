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
    private String flightNumber;

    @Column(nullable = false)
    @NotBlank(message = "source is required")
    private String source;

    @Column(nullable = false)
    @NotBlank(message = "destination is required")
    private String destination;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private Integer totalSeats;
}
