package com.kafka.order.application.service;

import com.kafka.order.application.dto.request.CreateOrderCommand;

public interface OrderEventProducer {

    void sendOrderCreatedEvent(CreateOrderCommand orderCommand, Long orderId);

}
