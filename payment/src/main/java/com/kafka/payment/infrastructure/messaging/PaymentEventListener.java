package com.kafka.payment.infrastructure.messaging;


import com.kafka.payment.application.service.PaymentService;
import com.kafka.payment.infrastructure.event.request.OrderCreatedEvent;
import com.kafka.payment.infrastructure.util.EventPayloadConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = "payment.ready")
    public void createPayment(String message) {
        log.info("이벤트 발생");
        OrderCreatedEvent createEvent = EventPayloadConverter.deserialize(message,
            OrderCreatedEvent.class);
        log.info("이벤트 직렬화 성공");
        paymentService.createPayment(createEvent.toPaymentCreateCommand());
    }
}
