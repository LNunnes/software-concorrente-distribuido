package com.ufg.inf.orderService.controller;

import com.ufg.inf.common.model.Order;
import com.ufg.inf.common.model.OrderItem;
import com.ufg.inf.orderService.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, List<OrderItem>> payload) {
        List<OrderItem> items = payload.get("items");
        if (items == null || items.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Order order = orderService.createOrder(items);

        return ResponseEntity.ok(order);
    }
}
