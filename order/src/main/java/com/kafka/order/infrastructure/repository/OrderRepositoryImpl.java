package com.kafka.order.infrastructure.repository;

import com.kafka.order.domain.entity.Order;
import com.kafka.order.domain.repository.OrderRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return orderJpaRepository.findById(orderId);
    }
}
