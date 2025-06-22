package com.ufg.inf.common.model;

public class InventoryEvent {

    private String orderId;
    private boolean success;

    public InventoryEvent() {}

    public InventoryEvent(String orderId, boolean success) {
        this.orderId = orderId;
        this.success = success;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
