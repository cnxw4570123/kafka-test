package com.kafka.payment.infrastructure.event.request;

import com.kafka.payment.application.command.request.PaymentCreateCommand;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record PaymentCreatedEvent(
    Long paymentId,
    Long orderId,
    BigDecimal totalPrice
) {

    public static PaymentCreatedEvent of(PaymentCreateCommand command, Long paymentId) {
        return PaymentCreatedEvent.builder()
            .paymentId(paymentId)
            .orderId(command.orderId())
            .totalPrice(command.totalPrice())
            .build();
    }
}
