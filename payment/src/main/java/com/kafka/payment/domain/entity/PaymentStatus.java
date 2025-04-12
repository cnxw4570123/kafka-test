package com.kafka.payment.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    CREATED("CREATED"),
    FAILED("FAILED"),
    COMPLETED("COMPLETED"),
    ;

    private final String status;
}
