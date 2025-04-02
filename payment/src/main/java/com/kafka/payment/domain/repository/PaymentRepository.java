package com.kafka.payment.domain.repository;

import com.kafka.payment.domain.entity.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
