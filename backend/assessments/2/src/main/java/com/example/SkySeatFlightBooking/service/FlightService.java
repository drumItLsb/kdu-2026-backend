package com.example.SkySeatFlightBooking.service;

import com.example.SkySeatFlightBooking.entity.Flight;
import com.example.SkySeatFlightBooking.repository.FlightRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public Flight addFlight(Flight flight) {
        try {
            System.out.println(flight.getFlightNumber());
            System.out.println(flight.getSource());
            return flightRepository.save(flight);
        } catch(DataIntegrityViolationException e) {
            // DataIntegrityViolationException: indicates database error like not unique, adding null
            throw new RuntimeException("Database error: "+ e.getMessage());
        }
    }

    public Flight updateFlight(Flight flight, String flightId) {
        if(flight.getTotalSeats() < 0) {
            throw new RuntimeException("Seats can't ne less than equal to zero");
        }
        Flight retrivedFlight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight with id: "+flightId+" not found"));
        retrivedFlight.setTotalSeats(flight.getTotalSeats());
        flightRepository.save(retrivedFlight);
        return retrivedFlight;
    }

    public Page<Flight> getAllFlights(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return flightRepository.findAll(pageable);
    }
}
