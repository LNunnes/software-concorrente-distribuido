package com.ufg.inf.orderService.service;

import com.ufg.inf.common.model.Order;
import com.ufg.inf.common.model.OrderItem;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final String TOPIC_ORDERS = "orders";

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order createOrder(List<OrderItem> items) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setTimestamp(LocalDateTime.now().toString());
        order.setItems(items);

        kafkaTemplate.send(TOPIC_ORDERS, order.getOrderId(), order);

        return order;
    }
}
