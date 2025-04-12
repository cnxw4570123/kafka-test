package com.kafka.payment.infrastructure.event.request;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderCreatedEvent(
    UUID orderId,
    String userEmail,
    String productInfo,
    BigDecimal totalPrice
) {

    public PaymentCreateCommand toPaymentCreateCommand() {
        return PaymentCreateCommand.builder()
            .orderId(this.orderId())
            .userEmail(this.userEmail())
            .productInfo(this.productInfo())
            .totalAmount(this.totalPrice())
            .build();
    }
}
