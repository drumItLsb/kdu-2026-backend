package com.example.SkySeatFlightBooking.controller;

import com.example.SkySeatFlightBooking.dto.HoldDto;
import com.example.SkySeatFlightBooking.entity.Flight;
import com.example.SkySeatFlightBooking.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@Valid @RequestBody Flight flight) {
        Flight createdBook = flightService.addFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{flightNumber}")
    public ResponseEntity<Flight> updateFlight(@Valid @RequestBody Flight flight,  @PathVariable String flightNumber) {
        Flight updatedFlight = flightService.updateFlight(flight,flightNumber);

        return ResponseEntity.ok(updatedFlight);
    }

    @GetMapping
    public ResponseEntity<Page<Flight>> getAllFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Flight> productPage = flightService.getAllFlights(page, size);
        // also send links
        return ResponseEntity.ok(productPage);
    }

}
