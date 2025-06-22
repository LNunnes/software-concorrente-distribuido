package com.ufg.inf.inventoryService.listener;

import com.ufg.inf.common.model.Order;
import com.ufg.inf.inventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "orders", groupId = "inventory-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Order order) {
        inventoryService.process(order);
    }
}
