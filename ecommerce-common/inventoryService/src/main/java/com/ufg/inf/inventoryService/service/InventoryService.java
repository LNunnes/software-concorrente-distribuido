package com.ufg.inf.inventoryService.service;

import com.ufg.inf.common.model.InventoryEvent;
import com.ufg.inf.common.model.Order;
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

    private boolean checkAndReserveStock(List<String> items) {
        for (String item : items) {
            StockItem stockItem = stockItemRepository.findById(item).orElse(null);
            if (stockItem == null || stockItem.getQuantity() <= 0) {
                return false; // sem estoque
            }
        }

        for (String item : items) {
            StockItem stockItem = stockItemRepository.findById(item).get();
            stockItem.setQuantity(stockItem.getQuantity() - 1);
            stockItemRepository.save(stockItem);
        }

        return true;
    }
}
