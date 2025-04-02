package com.kafka.payment.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
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
    private String userEmail;
    private String productInfo;
    private BigDecimal totalPrice;

    @Builder
    public Payment(Long id, String userEmail, String productInfo, BigDecimal totalPrice) {
        this.id = id;
        this.userEmail = userEmail;
        this.productInfo = productInfo;
        this.totalPrice = totalPrice;
    }
}
