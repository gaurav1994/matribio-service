package com.matribio.matribio_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_transactions_tbl")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String orderId;
    private String entity;
    private Integer amount;
    private Integer amountPaid;
    private String receiptId;
    private String status;
    private Integer attempts;
    private Integer createdAt;

    

    public PaymentTransaction() {
    }

    public PaymentTransaction(Integer id, String orderId, String entity, Integer amount, Integer amountPaid, String receiptId,
            String status, Integer attempts, Integer createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.entity = entity;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.receiptId = receiptId;
        this.status = status;
        this.attempts = attempts;
        this.createdAt = createdAt;
    }

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

    
}
