package com.example.__WebFlux.domain.payments.models;

import java.time.Instant;
import java.util.UUID;

public class PaymentModel {
    private UUID paymentId;
    private UUID orderId;
    private Double amount;
    private String currency;
    private String methodPay;
    private String statusPay;
    private Instant paidAt;

    public PaymentModel(UUID paymentId, UUID orderId, Double amount, String currency, String methodPay,
            String statusPay, Instant paidAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.methodPay = methodPay;
        this.statusPay = statusPay;
        this.paidAt = paidAt;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethodPay() {
        return methodPay;
    }

    public void setMethodPay(String methodPay) {
        this.methodPay = methodPay;
    }

    public String getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(String statusPay) {
        this.statusPay = statusPay;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }

}
