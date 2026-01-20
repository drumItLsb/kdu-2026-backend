package com.example.RailwayBookingSystem.service;

import com.example.RailwayBookingSystem.dto.PaymentDeductEvent;
import com.example.RailwayBookingSystem.entity.ProcessedEvent;
import com.example.RailwayBookingSystem.repository.ProcessedEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentListener.class);
    private final ProcessedEventRepository processedEventRepository;

    public PaymentListener(ProcessedEventRepository processedEventRepository) {
        this.processedEventRepository = processedEventRepository;
    }

    @Transactional
    @RabbitListener(queues = "payment.deduct.q")
    public void onPaymentDeduct(PaymentDeductEvent event) {
        try {
            processedEventRepository.save(new ProcessedEvent(event.getEventId()));
        } catch (DataIntegrityViolationException dup) {
            log.warn("PAYMENT duplicate ignored eventId={} paymentRef={}", event.getEventId(), event.getPaymentRef());
            return;
        }

        // Do the side-effect ONCE
        log.info("PAYMENT Money Deducted (ONCE) eventId={} paymentRef={} bookingId={} amountPaise={}",
                event.getEventId(), event.getPaymentRef(), event.getBookingId(), event.getAmountPaise());
    }
}
