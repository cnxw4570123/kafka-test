package com.kafka.order.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    PAYMENT_READY(Topic.PAYMENT_READY),
    ;

    private final String topic;

    public static class Topic {

        public static final String PAYMENT_READY = "payment.ready";
    }
}
