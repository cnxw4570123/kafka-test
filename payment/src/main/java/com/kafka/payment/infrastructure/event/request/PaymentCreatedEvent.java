package com.kafka.payment.infrastructure.event.request;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PaymentCreatedEvent(
    Long paymentId,
    UUID orderId,
    BigDecimal totalPrice
) {

    public static PaymentCreatedEvent of(PaymentCreateCommand command, Long paymentId) {
        return PaymentCreatedEvent.builder()
            .paymentId(paymentId)
            .orderId(command.orderId())
            .totalPrice(command.totalAmount())
            .build();
    }
}
