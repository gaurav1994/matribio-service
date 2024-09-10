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
    
    public PaymentTransactionDto() {
    }
    public PaymentTransactionDto(Integer id, String orderId, String entity, Integer amount, Integer amountPaid,
            String receiptId, String status, Integer attempts, Integer createdAt, String razorpayKeyId) {
        this.id = id;
        this.orderId = orderId;
        this.entity = entity;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.receiptId = receiptId;
        this.status = status;
        this.attempts = attempts;
        this.createdAt = createdAt;
        this.razorpayKeyId = razorpayKeyId;
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
    public String getRazorpayKeyId() {
        return razorpayKeyId;
    }
    public void setRazorpayKeyId(String razorpayKeyId) {
        this.razorpayKeyId = razorpayKeyId;
    }
    
    // Implement Builder Design Pattern.

    public static class PaymentTransactionDtoBuilder {
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

        public PaymentTransactionDtoBuilder id(Integer id) {
            this.id=id;
            return this;
        }
        public PaymentTransactionDtoBuilder orderId(String orderId) {
            this.orderId=orderId;
            return this;
        }
        public PaymentTransactionDtoBuilder entity(String entity) {
            this.entity=entity;
            return this;
        }
        public PaymentTransactionDtoBuilder amount(Integer amount) {
            this.amount=amount;
            return this;
        }
        public PaymentTransactionDtoBuilder amountPaid(Integer amountPaid) {
            this.amountPaid=amountPaid;
            return this;
        }
        public PaymentTransactionDtoBuilder receiptId(String receiptId) {
            this.receiptId=receiptId;
            return this;
        }
        public PaymentTransactionDtoBuilder status(String status) {
            this.status=status;
            return this;
        }
        public PaymentTransactionDtoBuilder attempts(Integer attempts) {
            this.attempts=attempts;
            return this;
        }
        public PaymentTransactionDtoBuilder createdAt(Integer createdAt) {
            this.createdAt=createdAt;
            return this;
        }
        public PaymentTransactionDtoBuilder razorpayKeyId(String razorpayKeyId) {
            this.razorpayKeyId=razorpayKeyId;
            return this;
        }
        
        public PaymentTransactionDto build() {
            return new PaymentTransactionDto(id, orderId, entity, amount, amountPaid, receiptId, status, attempts, createdAt, razorpayKeyId);
        }
    }

    public static PaymentTransactionDtoBuilder builder() {
        return new PaymentTransactionDtoBuilder();
    }
}
