package com.ufg.inf.common.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String orderId;
    private String timestamp;
    private List<OrderItem> items;

    public Order() {}

    public Order(String orderId, String timestamp, List<OrderItem> items) {
        this.orderId = orderId;
        this.timestamp = timestamp;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
