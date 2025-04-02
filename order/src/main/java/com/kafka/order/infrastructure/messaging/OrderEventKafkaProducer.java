package com.kafka.order.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.order.application.dto.request.CreateOrderCommand;
import com.kafka.order.application.service.OrderEventProducer;
import com.kafka.order.infrastructure.dto.request.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventKafkaProducer implements OrderEventProducer {

    private final KafkaTemplate<String, String> orderKafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String PAYMENT_READY = "payment.ready";

    public void sendOrderCreatedEvent(CreateOrderCommand orderCommand, Long orderId) {
        try {
            OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .orderId(orderId)
                .userEmail(orderCommand.userEmail())
                .totalPrice(orderCommand.totalPrice())
                .productInfo(orderCommand.productInfo())
                .build();

            orderKafkaTemplate.send(PAYMENT_READY,
                objectMapper.writeValueAsString(orderCreatedEvent));
        } catch (JsonProcessingException e) {
            log.error("주문 이벤트 직렬화 실패");
            throw new RuntimeException(e);
        }

    }
}
