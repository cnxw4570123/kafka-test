package com.kafka.order.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.order.application.dto.request.CreateOrderCommand;
import com.kafka.order.application.dto.request.CreateOrderCompletedEventCommand;
import com.kafka.order.application.dto.request.UpdateOrderCommand;
import com.kafka.order.domain.entity.EventType;
import com.kafka.order.domain.entity.Order;
import com.kafka.order.domain.entity.Outbox;
import com.kafka.order.domain.repository.OrderRepository;
import com.kafka.order.domain.repository.OutboxRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "주문 서비스")
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional
    public void creatOrder(CreateOrderCommand orderCommand) {
        Order order = orderCommand.toOrder();
        Order save = orderRepository.save(order);

        /* TODO: Outbox 저장
            Producer를 일정 주기마다 보내도록 변경
         */
        CreateOrderCompletedEventCommand createOrderCompletedEventCommand =
            CreateOrderCompletedEventCommand.of(save);

        Outbox outbox = null;
        try {
            outbox = Outbox.OutboxEventBuilder()
                .createdAt(LocalDateTime.now())
                .eventType(EventType.PAYMENT_READY)
                .payload(objectMapper.writeValueAsString(createOrderCompletedEventCommand))
                .build();
        } catch (JsonProcessingException e) {
            log.error("주문 생성 이벤트 발행 실패");
            throw new RuntimeException(e);
        }

        outboxRepository.save(outbox);
    }

    @Override
    @Transactional
    public void updateOrderPaymentSuccess(UpdateOrderCommand command) {
        Order order = orderRepository.findOrderById(command.orderId())
            .orElseThrow(() -> new RuntimeException("그런 주문 X"));

        order.updatePaymentSuccess(command.paymentId());
    }
}
