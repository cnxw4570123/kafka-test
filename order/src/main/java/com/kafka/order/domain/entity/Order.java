package com.kafka.order.domain.entity;

import jakarta.persistence.Table;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String userEmail;

    private Long paymentId;

    private String userAddress;

    private String productInfo;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Long orderId,
        String userEmail,
        Long paymentId,
        String userAddress,
        String productInfo,
        BigDecimal totalPrice,
        OrderStatus orderStatus
    ) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.paymentId = paymentId;
        this.userAddress = userAddress;
        this.productInfo = productInfo;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public void updatePaymentSuccess(Long paymentId) {
        this.orderStatus = OrderStatus.PAID;
        this.paymentId = paymentId;
    }
}
