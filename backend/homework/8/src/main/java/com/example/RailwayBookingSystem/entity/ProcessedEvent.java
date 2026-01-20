package com.example.RailwayBookingSystem.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
        name = "processed_event",
        uniqueConstraints = @UniqueConstraint(name = "uk_processed_event_event_id", columnNames = "eventId")
)
public class ProcessedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String eventId;

    @Column(nullable = false)
    private Instant processedAt;

    protected ProcessedEvent() {}

    public ProcessedEvent(String eventId) {
        this.eventId = eventId;
        this.processedAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getEventId() { return eventId; }
    public Instant getProcessedAt() { return processedAt; }
}
