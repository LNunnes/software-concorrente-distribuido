package com.ufg.inf.inventoryService.repository;

import com.ufg.inf.inventoryService.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, String> {
}
