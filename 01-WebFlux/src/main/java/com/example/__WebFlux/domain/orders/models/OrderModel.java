package com.example.__WebFlux.domain.orders.models;

import java.time.Instant;
import java.util.UUID;

public class OrderModel {
    private UUID orderId;
    private UUID userId;
    private Instant orderDate;
    private String status;
    private Double totalAmount;

    public OrderModel(UUID orderId, UUID userId, Instant orderDate, String status, Double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
