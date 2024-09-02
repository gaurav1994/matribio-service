package com.matribio.matribio_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matribio.matribio_service.entity.PaymentTransaction;


@Repository
public interface PaymentTransactionsRepository extends JpaRepository<PaymentTransaction, Integer> {

    PaymentTransaction findByOrderId(String orderId);
}
