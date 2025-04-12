package com.kafka.payment.application.dto.command;

import com.kafka.payment.domain.entity.Payment;
import com.kafka.payment.domain.entity.PaymentStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PaymentCreateCommand(
    Long userId,
    String userEmail,
    String userName,
    BigDecimal totalAmount,
    UUID orderId,
    String productInfo
) {

    public Payment toPayment() {
        return Payment.builder()
            .userId(userId)
            .userEmail(userEmail)
            .userName(userName)
            .totalAmount(totalAmount)
            .orderId(orderId)
            .productInfo(productInfo)
            .status(PaymentStatus.CREATED)
            .build();
    }
}
