package com.kafka.order.application.service;

import com.kafka.order.application.dto.request.CreateOrderCommand;
import com.kafka.order.application.dto.request.UpdateOrderCommand;

public interface OrderService {

    void creatOrder(CreateOrderCommand orderCommand);

    void updateOrderPaymentSuccess(UpdateOrderCommand command);
}
