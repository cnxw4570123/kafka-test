package com.kafka.payment.application.dto.query;

import com.kafka.payment.domain.entity.Payment;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FindPaymentQuery(
    Long userId,
    String userEmail,
    String userName,
    BigDecimal totalAmount,
    UUID orderId,
    String productInfo
) {

    public static FindPaymentQuery of(Payment payment) {
        return FindPaymentQuery.builder()
            .userId(payment.getUserId())
            .userEmail(payment.getUserEmail())
            .userName(payment.getUserName())
            .totalAmount(payment.getTotalAmount())
            .orderId(payment.getOrderId())
            .productInfo(payment.getProductInfo())
            .build();
    }
}
