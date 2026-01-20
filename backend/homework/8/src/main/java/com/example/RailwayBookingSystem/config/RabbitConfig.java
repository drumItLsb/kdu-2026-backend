package com.example.RailwayBookingSystem.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange railwayEventsExchange(@Value("${railway.rabbit.exchange}") String name) {
        return new TopicExchange(name, true, false);
    }

    @Bean
    public Queue inventoryQueue(@Value("${railway.rabbit.inventoryQueue}") String q) {
        return new Queue(q, true);
    }

    @Bean
    public Queue notificationQueue(@Value("${railway.rabbit.notificationQueue}") String q) {
        return new Queue(q, true);
    }

    @Bean
    public Queue paymentQueue(@Value("${railway.rabbit.paymentQueue}") String q) {
        return new Queue(q, true);
    }

    @Bean
    public Binding inventoryBinding(Queue inventoryQueue,
                                    TopicExchange railwayEventsExchange,
                                    @Value("${railway.rabbit.ticketBookedRoutingKey}") String rk) {
        return BindingBuilder.bind(inventoryQueue).to(railwayEventsExchange).with(rk);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue,
                                       TopicExchange railwayEventsExchange,
                                       @Value("${railway.rabbit.ticketBookedRoutingKey}") String rk) {
        return BindingBuilder.bind(notificationQueue).to(railwayEventsExchange).with(rk);
    }

    @Bean
    public Binding paymentBinding(Queue paymentQueue,
                                  TopicExchange railwayEventsExchange,
                                  @Value("${railway.rabbit.paymentRoutingKey}") String rk) {
        return BindingBuilder.bind(paymentQueue).to(railwayEventsExchange).with(rk);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        // Replacement for Jackson2JsonMessageConverter
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, MessageConverter conv) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(conv);
        return tpl;
    }
}

