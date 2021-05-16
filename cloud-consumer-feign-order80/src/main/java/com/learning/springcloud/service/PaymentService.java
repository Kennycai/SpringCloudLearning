package com.learning.springcloud.service;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {
    @PostMapping("/payment/create")
    CommonResult<Payment> create(Payment payment);

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeout")
    CommonResult<Payment> timeout();
}
