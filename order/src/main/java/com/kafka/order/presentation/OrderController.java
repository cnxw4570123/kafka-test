package com.kafka.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.order.application.service.OrderService;
import com.kafka.order.application.dto.request.CreateOrderCommand;
import com.kafka.order.presentation.dto.request.CreateOrderRequest;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderCommand createOrderCommand = createOrderRequest.toCreateOrderCommand();
        orderService.creatOrder(createOrderCommand);
    }
}
