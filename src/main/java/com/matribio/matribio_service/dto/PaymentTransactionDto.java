package com.matribio.matribio_service.dto;

public class PaymentTransactionDto {

    private Integer id;
    private String orderId;
    private String entity;
    private Integer amount;
    private Integer amountPaid;
    private String receiptId;
    private String status;
    private Integer attempts;
    private Integer createdAt;
    private String razorpayKeyId;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getEntity() {
        return entity;
    }
    public void setEntity(String entity) {
        this.entity = entity;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }
    public String getReceiptId() {
        return receiptId;
    }
    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getAttempts() {
        return attempts;
    }
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }
    public Integer getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
    public String getRazorpayKeyId() {
        return razorpayKeyId;
    }
    public void setRazorpayKeyId(String razorpayKeyId) {
        this.razorpayKeyId = razorpayKeyId;
    }
    
    
}
