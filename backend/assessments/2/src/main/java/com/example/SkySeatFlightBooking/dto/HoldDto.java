package com.example.SkySeatFlightBooking.dto;

public class HoldDto {
    private String flightNumber;
    private Integer seatsCount;

    public HoldDto(String flightNumber, Integer seatsCount) {
        this.flightNumber = flightNumber;
        this.seatsCount = seatsCount;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }
}
