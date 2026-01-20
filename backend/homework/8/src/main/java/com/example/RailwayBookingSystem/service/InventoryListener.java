package com.example.RailwayBookingSystem.service;

import com.example.RailwayBookingSystem.dto.TicketBookedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryListener {
    private static final Logger log = LoggerFactory.getLogger(InventoryListener.class);

    @RabbitListener(queues = "${railway.rabbit.inventoryQueue}")
    public void onTicketBooked(TicketBookedEvent event) {
        log.info("INVENTORY received TicketBooked eventId={} bookingId={} seat={}",
                event.getEventId(), event.getBookingId(), event.getSeatNo());

        log.info("INVENTORY seat marked OCCUPIED bookingId={} seat={}", event.getBookingId(), event.getSeatNo());
    }
}
