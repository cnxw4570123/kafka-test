package com.kafka.payment.application.service;

import com.kafka.payment.application.command.request.PaymentCreateCommand;

public interface PaymentService {

    void createPayment(PaymentCreateCommand paymentCreateCommand);
}
