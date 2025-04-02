package com.kafka.payment.application.command.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record PaymentCreateCommand(
    Long orderId,
    String userEmail,
    String productInfo,
    BigDecimal totalPrice
) {

}
