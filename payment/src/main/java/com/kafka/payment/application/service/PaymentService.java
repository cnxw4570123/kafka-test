package com.kafka.payment.application.service;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;
import com.kafka.payment.application.dto.query.FindPaymentQuery;
import java.util.UUID;

public interface PaymentService {

    void createPayment(PaymentCreateCommand paymentCreateCommand);

    Long savePayment(PaymentCreateCommand paymentCreateRequest);

    FindPaymentQuery findPayment(Long paymentId);
}
