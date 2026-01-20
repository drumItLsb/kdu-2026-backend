package com.example.RailwayBookingSystem.controller;

import com.example.RailwayBookingSystem.dto.PaymentDeductEvent;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestPublisherController {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange exchange;

    public TestPublisherController(RabbitTemplate rabbitTemplate, TopicExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @PostMapping("/payment-dup")
    public String publishPaymentDup() {
        String eventId = "SAME-EVENT-ID-124"; // intentionally same
        PaymentDeductEvent e = new PaymentDeductEvent(eventId, "PAYREF-888", "BKG-2", 35000);

        for (int i = 0; i < 3; i++) {
            rabbitTemplate.convertAndSend(exchange.getName(), "payment.deduct", e);
        }
        return "Published same payment message 3 times";
    }
}
