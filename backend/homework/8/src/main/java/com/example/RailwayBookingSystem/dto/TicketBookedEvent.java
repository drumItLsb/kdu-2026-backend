package com.example.RailwayBookingSystem.dto;

public class TicketBookedEvent {
    private String eventId;
    private String bookingId;
    private String userId;
    private String trainId;
    private String seatNo;
    private long bookedAtEpochMs;

    public TicketBookedEvent(String eventId, String bookingId, String userId, String trainId, String seatNo, long bookedAtEpochMs) {
        this.eventId = eventId;
        this.bookingId = bookingId;
        this.userId = userId;
        this.trainId = trainId;
        this.seatNo = seatNo;
        this.bookedAtEpochMs = bookedAtEpochMs;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public long getBookedAtEpochMs() {
        return bookedAtEpochMs;
    }

    public void setBookedAtEpochMs(long bookedAtEpochMs) {
        this.bookedAtEpochMs = bookedAtEpochMs;
    }
}
