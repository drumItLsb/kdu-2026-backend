package com.example.RailwayBookingSystem.controller;

import com.example.RailwayBookingSystem.dto.TicketBookedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange exchange;
    private final String ticketBookedRoutingKey;

    public BookingController(RabbitTemplate rabbitTemplate,
                             TopicExchange exchange,
                             @Value("${railway.rabbit.ticketBookedRoutingKey}") String rk) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.ticketBookedRoutingKey = rk;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBooking(@RequestBody Map<String, String> req) {
        String bookingId = UUID.randomUUID().toString();
        String eventId = UUID.randomUUID().toString();

        TicketBookedEvent event = new TicketBookedEvent(
                eventId,
                bookingId,
                req.getOrDefault("userId", "u1"),
                req.getOrDefault("trainId", "t1"),
                req.getOrDefault("seatNo", "S1"),
                System.currentTimeMillis()
        );

        rabbitTemplate.convertAndSend(exchange.getName(), ticketBookedRoutingKey, event, msg -> {
            msg.getMessageProperties().setMessageId(eventId);
            msg.getMessageProperties().setHeader("eventType", "TicketBooked");
            return msg;
        });

        log.info("BOOKING accepted bookingId={} eventId={} (published TicketBooked)", bookingId, eventId);

        return ResponseEntity.accepted().body(Map.of(
                "status", "BOOKING_IN_PROGRESS",
                "bookingId", bookingId
        ));
    }
}

