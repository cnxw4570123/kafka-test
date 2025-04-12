package com.kafka.payment.presentation.dto.response;

import com.kafka.payment.application.dto.query.FindPaymentQuery;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FindPaymentResponse(
    String userId,
    String userEmail,
    String userName,
    BigDecimal totalAmount,
    UUID orderId,
    String productInfo
) {

    public static FindPaymentResponse of(FindPaymentQuery payment) {
        return FindPaymentResponse.builder()
            .userId("PAY_" + payment.userId())
            .userEmail(payment.userEmail())
            .userName(payment.userName())
            .totalAmount(payment.totalAmount())
            .orderId(payment.orderId())
            .productInfo(payment.productInfo())
            .build();
    }
}
