package com.learning.springcloud.service;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/payment/ok")
    CommonResult<Payment> ok();

    @GetMapping(value = "/payment/timeout")
    CommonResult<String> timeout();
}
