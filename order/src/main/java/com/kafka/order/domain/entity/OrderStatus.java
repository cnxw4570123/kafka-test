package com.kafka.order.domain.entity;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    CREATED("CREATED"),
    PAYMENT_PENDING("PAYMENT_PENDING"),
    PAYMENT_FAILED("PAYMENT_FAILED"),
    PAID("PAID"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED"),
    ;
    private final String status;

    private OrderStatus from(String status) {
        return Arrays.stream(OrderStatus.values())
            .filter(orderStatus -> orderStatus.status.equals(status))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("해당 상태값 없음"));
    }
}
