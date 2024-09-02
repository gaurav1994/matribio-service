package com.matribio.matribio_service.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.entity.PaymentTransaction;
import com.matribio.matribio_service.repository.PaymentTransactionsRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentTransactionServiceImp implements PaymentTransactionService {

    @Autowired
    PaymentTransactionsRepository transactionsRepository;

    @Value("${razorpay.key.id}")
    private String RAZORPAY_KEY_ID;

    @Value("${razorpay.key.secret}")
    private String RAZORPAY_KEY_SECRET;

    private static final String CURRENCY = "INR";


    @Override
    public PaymentTransaction createOrderForTransaction(Integer amount) {
        try {
            RazorpayClient razorpay = new RazorpayClient(RAZORPAY_KEY_ID, RAZORPAY_KEY_SECRET);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100);
            orderRequest.put("currency", CURRENCY);
            orderRequest.put("receipt", generateRecieptId());
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1","It's very hot day today");
            orderRequest.put("notes",notes);
            orderRequest.put("payment_capture", 1);
            Order order = razorpay.orders.create(orderRequest);

            PaymentTransaction savedPaymentTransaction = getSavedOrUpdatePaymentTransaction(order, null);
            return savedPaymentTransaction;
        } catch(RazorpayException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public SimpleMessage updatePaymentTransactionToDB(String orderId) {
        try {
            RazorpayClient razorpay = new RazorpayClient(RAZORPAY_KEY_ID, RAZORPAY_KEY_SECRET);
            Order order = razorpay.orders.fetch(orderId);

            PaymentTransaction savedOrderTransaction = transactionsRepository.findByOrderId(orderId);
            
            PaymentTransaction savedPaymentTransaction = getSavedOrUpdatePaymentTransaction(order, savedOrderTransaction.getId());
            
            return new SimpleMessage("Transaction Completed Successfully! With Status: "+ savedPaymentTransaction.getStatus() );
        } catch (RazorpayException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private static String generateRecieptId() {
        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return "MAT" + generatedString.toUpperCase();
    }

    private PaymentTransaction getSavedOrUpdatePaymentTransaction(Order order, Integer dbTransactionId) {
        PaymentTransaction orderSavedToDb = new PaymentTransaction(
                                                    dbTransactionId,
                                                    order.get("id"), 
                                                    order.get("entity"),
                                                    (Integer) order.get("amount")/100,
                                                    (Integer) order.get("amount_paid"),
                                                    order.get("receipt"),
                                                    order.get("status"),
                                                    (Integer) order.get("attempts"), 
                                                    null);

        PaymentTransaction savedPaymentTransaction = transactionsRepository.save(orderSavedToDb);
        return savedPaymentTransaction;
    }

}
