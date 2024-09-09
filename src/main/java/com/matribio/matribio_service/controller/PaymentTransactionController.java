package com.matribio.matribio_service.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matribio.matribio_service.dto.PaymentTransactionDto;
import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.entity.PaymentTransaction;
import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.service.PaymentTransactionService;
import com.matribio.matribio_service.service.UserBiodataService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment")
public class PaymentTransactionController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserBiodataService userBiodataService;

    @Value("${razorpay.key.id}")
    private String RAZORPAY_KEY_ID;

    @Autowired
    PaymentTransactionService paymentTransactionService;

    @GetMapping("/create-transaction")
    public ResponseEntity<PaymentTransactionDto> createPaymentOrder(@RequestParam() Integer amount, @RequestParam() Integer biodataId) {
        PaymentTransaction orderCreated = paymentTransactionService.createOrderForTransaction(amount);
        if (orderCreated == null) {
            throw new RuntimeException("Order not created!");
        }
        updateUserBiodata(biodataId, orderCreated);

        PaymentTransactionDto orderCreatedDto = modelMapper.map(orderCreated, PaymentTransactionDto.class);
        orderCreatedDto.setRazorpayKeyId(RAZORPAY_KEY_ID);
        return ResponseEntity.ok(orderCreatedDto);
    }

    private void updateUserBiodata(Integer biodataId, PaymentTransaction orderCreated) {
        UserBiodata userBiodata = new UserBiodata();
        userBiodata.setReceiptId(orderCreated.getReceiptId());
        userBiodataService.updateUserBiodata(biodataId, userBiodata);
    }

    @GetMapping("update-transaction")
    public ResponseEntity<SimpleMessage> putMethodName(@RequestParam() String orderId) {

        SimpleMessage paymentTransactionStatus = paymentTransactionService.updatePaymentTransactionToDB(orderId);
        if (paymentTransactionStatus == null) {
            throw new RuntimeException("Transaction Not Completed ! ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(paymentTransactionStatus);

    }
}
