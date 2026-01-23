package com.example.SkySeatFlightBooking.controller;

import com.example.SkySeatFlightBooking.dto.HoldDto;
import com.example.SkySeatFlightBooking.dto.HoldUpdateDto;
import com.example.SkySeatFlightBooking.repository.SeatsRepository;
import com.example.SkySeatFlightBooking.service.FlightService;
import com.example.SkySeatFlightBooking.service.SeatsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/holds")
public class SeatsHoldController {
    private final FlightService flightService;
    private final SeatsService seatsService;


    public SeatsHoldController(FlightService flightService, SeatsService seatsService) {
        this.flightService = flightService;
        this.seatsService = seatsService;
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> addHold(@Valid @RequestBody HoldDto flightDto) {
        Map<String,String> result = new HashMap<>();

        result.put("holdId", flightService.addHold(flightDto));

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{holdId}")
    public ResponseEntity<Map<String,String>> updateHold(@Valid @RequestBody HoldUpdateDto seatUpdateDto, @PathVariable String holdId) {
        Map<String,String> result = new HashMap<>();
        seatsService.updateHold(seatUpdateDto,holdId);
        result.put("res", "updated successfully");
        return ResponseEntity.ok(result);
    }
}
