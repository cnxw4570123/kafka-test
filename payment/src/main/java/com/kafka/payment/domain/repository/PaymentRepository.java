package com.kafka.payment.domain.repository;

import com.kafka.payment.application.dto.query.FindPaymentQuery;
import com.kafka.payment.domain.entity.Payment;
import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);
}
