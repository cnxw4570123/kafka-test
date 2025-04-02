package com.kafka.payment.infrastructure.event.request;

import com.kafka.payment.application.command.request.PaymentCreateCommand;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record OrderCreatedEvent(
    Long orderId,
    String userEmail,
    String productInfo,
    BigDecimal totalPrice
) {

    public PaymentCreateCommand toPaymentCreateCommand() {
        return PaymentCreateCommand.builder()
            .orderId(this.orderId())
            .userEmail(this.userEmail())
            .productInfo(this.productInfo())
            .totalPrice(this.totalPrice())
            .build();
    }
}
