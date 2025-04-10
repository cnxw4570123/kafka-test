package com.kafka.order.application.dto.request;

import com.kafka.order.domain.entity.Order;
import com.kafka.order.domain.entity.OrderStatus;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record CreateOrderCompletedEventCommand(
    Long orderId,
    String userEmail,
    String productInfo,
    BigDecimal totalPrice
) {

    public static CreateOrderCompletedEventCommand of(Order order) {
        return CreateOrderCompletedEventCommand.builder()
            .orderId(order.getOrderId())
            .userEmail(order.getUserEmail())
            .productInfo(order.getProductInfo())
            .totalPrice(order.getTotalPrice())
            .build();
    }
}
