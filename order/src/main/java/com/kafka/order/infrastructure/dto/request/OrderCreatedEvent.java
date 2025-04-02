package com.kafka.order.infrastructure.dto.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record OrderCreatedEvent(
    Long orderId,
    String userEmail,
    String productInfo,
    BigDecimal totalPrice
) {

}
