package com.kafka.order.application.dto.request;

import lombok.Builder;

@Builder
public record UpdateOrderCommand(
    Long orderId,
    Long paymentId
) {

}
