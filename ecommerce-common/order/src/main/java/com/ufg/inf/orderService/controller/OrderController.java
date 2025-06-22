package com.ufg.inf.orderService.controller;

import com.ufg.inf.common.model.Order;
import com.ufg.inf.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, List<String>> payload) {
        List<String> items = payload.get("items");
        if (items == null || items.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Order order = orderService.createOrder(items);

        return ResponseEntity.ok(order);
    }
}
