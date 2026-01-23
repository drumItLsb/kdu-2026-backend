package com.example.SkySeatFlightBooking.service;

import com.example.SkySeatFlightBooking.dto.HoldUpdateDto;
import com.example.SkySeatFlightBooking.entity.Flight;
import com.example.SkySeatFlightBooking.entity.Seats;
import com.example.SkySeatFlightBooking.repository.FlightRepository;
import com.example.SkySeatFlightBooking.repository.SeatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatsService {
    private final SeatsRepository seatsRepository;
    private final FlightRepository flightRepository;

    public SeatsService(SeatsRepository seatsRepository, FlightRepository flightRepository) {
        this.seatsRepository = seatsRepository;
        this.flightRepository = flightRepository;
    }

    public void updateHold(HoldUpdateDto seatUpdateDto,String holdId) {
        List<Seats> seats = seatsRepository.findByHoldId(holdId);
        int freedSeats = 0;

        int requestedSeats = seatUpdateDto.getSeatsCount();

        for(Seats seat : seats) {
            if(!seat.isBooked() && !seat.isCancelled()) {
                seatsRepository.deleteRowBySeatNumber(seat.getSeatNumber());
                freedSeats++;
            }
        }

        String flightNumber = seats.get(0).getFlight().getFlightNumber();
        Flight flight = flightRepository.findById(flightNumber).orElseThrow(() -> new RuntimeException("Flight with id: "+flightNumber+" not found"));
        flight.setTotalSeats(flight.getTotalSeats()+freedSeats);
        flightRepository.save(flight);
    }

}
