package com.kafka.payment.presentation.dto.request;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PaymentCreateRequest(
    Long userId,
    String userEmail,
    String userName,
    BigDecimal totalAmount,
    UUID orderId,
    String productInfo
) {

    public PaymentCreateCommand toPaymentCreateCommand() {
        return PaymentCreateCommand.builder()
            .userId(userId)
            .userEmail(userEmail)
            .userName(userName)
            .totalAmount(totalAmount)
            .orderId(orderId)
            .productInfo(productInfo)
            .build();
    }
}
