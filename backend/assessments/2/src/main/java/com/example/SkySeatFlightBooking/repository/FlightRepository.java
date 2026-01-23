package com.example.SkySeatFlightBooking.repository;

import com.example.SkySeatFlightBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
}