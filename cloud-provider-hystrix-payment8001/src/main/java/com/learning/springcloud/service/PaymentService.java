package com.learning.springcloud.service;

import com.learning.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);
     Payment getPaymentById(@Param("id") Long id);

    String paymentCircuitBreaker(Integer id);
}
