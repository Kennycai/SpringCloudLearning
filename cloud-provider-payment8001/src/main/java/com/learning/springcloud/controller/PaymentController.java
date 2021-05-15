package com.learning.springcloud.controller;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import com.learning.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        return new CommonResult<>(200, "success", paymentService.create(payment));
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Integer id) {
        return new CommonResult<>(200, "success", paymentService.getPaymentById(Long.valueOf(id)));
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery () {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:" + service);
        }
        return discoveryClient;
    }
}
