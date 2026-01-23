package com.example.SkySeatFlightBooking.repository;

import com.example.SkySeatFlightBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Flight,String> {
}