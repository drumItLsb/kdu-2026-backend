package com.example.RailwayBookingSystem.dto;

public class PaymentDeductEvent {
    private String eventId;
    private String paymentRef;
    private String bookingId;
    private long amountPaise;

    public PaymentDeductEvent(String eventId, String paymentRef, String bookingId, long amountPaise) {
        this.eventId = eventId;
        this.paymentRef = paymentRef;
        this.bookingId = bookingId;
        this.amountPaise = amountPaise;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public long getAmountPaise() {
        return amountPaise;
    }

    public void setAmountPaise(long amountPaise) {
        this.amountPaise = amountPaise;
    }
}
