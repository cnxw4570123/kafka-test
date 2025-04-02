package com.kafka.order.infrastructure.dto.request;


import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record PaymentSuccessEvent(
    Long paymentId,
    Long orderId,
    BigDecimal totalPrice
) {

}
