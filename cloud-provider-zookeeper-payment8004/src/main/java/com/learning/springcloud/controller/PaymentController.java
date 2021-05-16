package com.learning.springcloud.controller;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import com.learning.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        return new CommonResult<>(200, "success", paymentService.create(payment));
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Integer id) {
        return new CommonResult<>(200, "success", paymentService.getPaymentById(Long.valueOf(id)));
    }

    @GetMapping(value = "/payment/zookeeper")
    public String discovery() {
        return "spring cloud with zookeeper:" + servicePort;
    }
}
