package com.kafka.order.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Table(name = "outbox")
@SQLRestriction("success is false")
@NoArgsConstructor
public class Outbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Timestamp
    @Column(name = "send_at")
    private LocalDateTime sendAt;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String payload;
    private boolean success;
    @Column(name = "retry_count")
    private Integer retryCount = 0;

    @Builder(builderMethodName = "OutboxEventBuilder")
    public Outbox(
        EventType eventType,
        LocalDateTime createdAt,
        String payload
    ) {
        this.eventType = eventType;
        this.createdAt = createdAt;
        this.payload = payload;
    }

    public void markAsSuccess() {
        this.success = true;
        this.sendAt = LocalDateTime.now();
    }

    public void increaseRetryCount() {
        this.retryCount++;
    }
}
