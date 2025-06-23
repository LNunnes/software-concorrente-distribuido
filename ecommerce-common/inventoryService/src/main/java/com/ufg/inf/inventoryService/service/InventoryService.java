package com.ufg.inf.inventoryService.service;

import com.ufg.inf.common.model.InventoryEvent;
import com.ufg.inf.common.model.Order;
import com.ufg.inf.common.model.OrderItem;
import com.ufg.inf.inventoryService.model.StockItem;
import com.ufg.inf.inventoryService.repository.StockItemRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final StockItemRepository stockItemRepository;
    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    public InventoryService(StockItemRepository stockItemRepository, KafkaTemplate<String, InventoryEvent> kafkaTemplate) {
        this.stockItemRepository = stockItemRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void process(Order order) {
        boolean available = checkAndReserveStock(order.getItems());
        InventoryEvent event = new InventoryEvent(order.getOrderId(), available);
        kafkaTemplate.send("inventory-events", order.getOrderId(), event);
    }

    private boolean checkAndReserveStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            StockItem stockItem = stockItemRepository.findById(item.getProductId()).orElse(null);
            if (stockItem == null || stockItem.getStock() < item.getQuantity()) {
                return false; // estoque insuficiente ou produto não existe
            }
        }

        // Se chegou aqui, tem estoque suficiente, vamos reservar (diminuir)
        for (OrderItem item : items) {
            StockItem stockItem = stockItemRepository.findById(item.getProductId()).get();
            stockItem.setStock(stockItem.getStock() - item.getQuantity());
            stockItemRepository.save(stockItem);
        }

        return true;
    }
}
