package com.kafka.order.application.service;

import com.kafka.order.application.dto.request.CreateOrderCommand;
import com.kafka.order.application.dto.request.UpdateOrderCommand;
import com.kafka.order.domain.entity.Order;
import com.kafka.order.domain.repository.OrderRepository;
import com.kafka.order.infrastructure.dto.request.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer producer;

    @Transactional
    public void creatOrder(CreateOrderCommand orderCommand) {
        Order order = orderCommand.toOrder();
        Order save = orderRepository.save(order);

        producer.sendOrderCreatedEvent(orderCommand, save.getOrderId());
    }

    @Transactional
    @Override
    public void updateOrderPaymentSuccess(UpdateOrderCommand command) {
        Order order = orderRepository.findOrderById(command.orderId())
            .orElseThrow(() -> new RuntimeException("그런 주문 X"));

        order.updatePaymentSuccess();
    }
}
