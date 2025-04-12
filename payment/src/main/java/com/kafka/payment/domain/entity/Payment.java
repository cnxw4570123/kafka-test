package com.kafka.payment.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userEmail;
    private String userName;
    private BigDecimal totalAmount;
    private String paymentKey;
    private UUID orderId;
    private String productInfo;
    private PaymentStatus status;

    @Builder
    public Payment(
        Long userId,
        String userEmail,
        String userName,
        BigDecimal totalAmount,
        UUID orderId,
        String productInfo,
        PaymentStatus status
    ) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.totalAmount = totalAmount;
        this.orderId = orderId;
        this.productInfo = productInfo;
        this.status = status;
    }
}
