package com.matribio.matribio_service.service;


import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.entity.PaymentTransaction;

public interface PaymentTransactionService {

    PaymentTransaction createOrderForTransaction(Integer amount);

    SimpleMessage updatePaymentTransactionToDB(String orderId);

    PaymentTransaction getPaymentbyReceiptId(String receiptId);
}
