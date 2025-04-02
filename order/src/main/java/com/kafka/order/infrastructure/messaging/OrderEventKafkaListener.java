package com.kafka.order.infrastructure.messaging;

import com.kafka.order.application.dto.request.CreateOrderCommand;
import com.kafka.order.application.dto.request.UpdateOrderCommand;
import com.kafka.order.application.service.OrderService;
import com.kafka.order.infrastructure.dto.request.PaymentSuccessEvent;
import com.kafka.order.infrastructure.util.EventPayloadConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventKafkaListener {

    private final OrderService orderService;

    @KafkaListener(topics = "payment.success", groupId = "payment-group")
    public void receivePaymentCreatedEvent(String message) {
        log.info("결제 성공 이벤트 수신");
        PaymentSuccessEvent paymentSuccess = EventPayloadConverter.deserialize(message,
            PaymentSuccessEvent.class);
        log.info("결제 성공 이벤트 성공");

        UpdateOrderCommand update = UpdateOrderCommand.builder()
            .orderId(paymentSuccess.orderId())
            .paymentId(paymentSuccess.paymentId())
            .build();

        orderService.updateOrderPaymentSuccess(update);

    }
}
