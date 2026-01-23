package com.example.SkySeatFlightBooking.service;

import com.example.SkySeatFlightBooking.dto.HoldDto;
import com.example.SkySeatFlightBooking.entity.Flight;
import com.example.SkySeatFlightBooking.entity.Seats;
import com.example.SkySeatFlightBooking.repository.FlightRepository;
import com.example.SkySeatFlightBooking.repository.SeatsRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final SeatsRepository seatsRepository;

    public FlightService(FlightRepository flightRepository,SeatsRepository seatsRepository) {
        this.flightRepository = flightRepository;
        this.seatsRepository = seatsRepository;
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

    public String addHold(HoldDto flightDto) {
        String flightNumber = flightDto.getFlightNumber();
        Flight fetchedFlight = flightRepository.findById(flightNumber).orElseThrow(() -> new RuntimeException("Flight with id: "+flightNumber+" not found"));

        int availableSeats = fetchedFlight.getTotalSeats();
        int requestedSeats = flightDto.getSeatsCount(), originalRequestedSeats = requestedSeats;
        if(availableSeats < requestedSeats) {
            throw new RuntimeException("seats are not available");
        }

        String holdId = UUID.randomUUID().toString();

        for(int currentSeat=1;currentSeat<=availableSeats && requestedSeats > 0;currentSeat++) {
            if(!seatsRepository.existsBySeatNumber(currentSeat)) {
                Seats seat = new Seats(currentSeat,holdId,true,fetchedFlight,false,false);
                seatsRepository.save(seat);
                requestedSeats--;
            }
        }

        fetchedFlight.setTotalSeats(availableSeats-originalRequestedSeats);

        return holdId;
    }
}
