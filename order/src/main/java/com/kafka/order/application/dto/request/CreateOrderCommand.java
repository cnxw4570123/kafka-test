package com.kafka.order.application.dto.request;

import com.kafka.order.domain.entity.Order;
import com.kafka.order.domain.entity.OrderStatus;
import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CreateOrderCommand(
    String userEmail,
    String address,
    String productInfo,
    BigDecimal totalPrice
) {

    public Order toOrder() {
        return Order.builder()
            .userEmail(this.userEmail)
            .userAddress(this.address)
            .totalPrice(this.totalPrice)
            .orderStatus(OrderStatus.PAYMENT_PENDING)
            .productInfo(this.productInfo)
            .build();
    }
}
