package com.kafka.payment.infrastructure.messaging;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;
import com.kafka.payment.application.service.PaymentEventProducer;
import com.kafka.payment.infrastructure.event.request.PaymentCreatedEvent;
import com.kafka.payment.infrastructure.util.EventPayloadConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaEventProducer implements PaymentEventProducer {

    private static final String PAYMENT_CREATED_TOPIC = "payment.success";
    private final KafkaTemplate<String, String> paymentKafkaTemplate;

    @Override
    public void sendPaymentCreatedEvent(PaymentCreateCommand paymentCreateCommand, Long id) {
        PaymentCreatedEvent event = PaymentCreatedEvent.of(paymentCreateCommand, id);
        paymentKafkaTemplate.send(PAYMENT_CREATED_TOPIC, EventPayloadConverter.serialize(event));
    }
}
