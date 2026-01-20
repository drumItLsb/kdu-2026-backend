package com.example.RailwayBookingSystem.service;

import com.example.RailwayBookingSystem.dto.TicketBookedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @RabbitListener(queues = "${railway.rabbit.notificationQueue}")
    public void onTicketBooked(TicketBookedEvent event) {
        log.info("NOTIFICATION received TicketBooked eventId={} bookingId={}",
                event.getEventId(), event.getBookingId());

        log.info("NOTIFICATION SMS sent for bookingId={} seat={}", event.getBookingId(), event.getSeatNo());
    }
}
