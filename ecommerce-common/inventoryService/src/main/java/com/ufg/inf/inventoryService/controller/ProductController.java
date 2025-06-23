package com.ufg.inf.inventoryService.controller;

import com.ufg.inf.inventoryService.model.StockItem;
import com.ufg.inf.inventoryService.repository.StockItemRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final StockItemRepository stockItemRepository;

    public ProductController(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @GetMapping
    public List<StockItem> getAllProducts() {
        return stockItemRepository.findAll();
    }
}
