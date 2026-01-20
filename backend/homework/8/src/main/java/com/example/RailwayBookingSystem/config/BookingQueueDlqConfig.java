package com.example.RailwayBookingSystem.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BookingQueueDlqConfig {

    @Bean
    public DirectExchange bookingDlx(@Value("${railway.rabbit.bookingDlx}") String dlxName) {
        return new DirectExchange(dlxName, true, false);
    }

    @Bean
    public Queue bookingErrorQueue(@Value("${railway.rabbit.bookingDlq}") String dlqName) {
        return new Queue(dlqName, true);
    }

    @Bean
    public Binding bookingErrorBinding(Queue bookingErrorQueue,
                                       DirectExchange bookingDlx) {
        // routing key for dead-lettering; keep it simple
        return BindingBuilder.bind(bookingErrorQueue).to(bookingDlx).with("booking.error");
    }

    @Bean
    public Queue bookingMainQueue(@Value("${railway.rabbit.bookingQueue}") String qName,
                                  @Value("${railway.rabbit.bookingDlx}") String dlxName) {

        Map<String, Object> args = new HashMap<>();
        // When a message is rejected (requeue=false) or expires, send to DLX:
        args.put("x-dead-letter-exchange", dlxName);
        // Use this routing key when dead-lettering:
        args.put("x-dead-letter-routing-key", "booking.error");

        return new Queue(qName, true, false, false, args);
    }
}
