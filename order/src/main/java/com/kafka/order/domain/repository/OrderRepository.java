package com.kafka.order.domain.repository;

import com.kafka.order.domain.entity.Order;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findOrderById(Long orderId);
}
