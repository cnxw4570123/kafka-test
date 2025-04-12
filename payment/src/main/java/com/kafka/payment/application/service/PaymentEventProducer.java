package com.kafka.payment.application.service;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;

public interface PaymentEventProducer {

    void sendPaymentCreatedEvent(PaymentCreateCommand paymentCreateCommand, Long id);

}
