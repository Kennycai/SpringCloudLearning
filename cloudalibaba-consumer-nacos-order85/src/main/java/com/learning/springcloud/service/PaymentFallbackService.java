package com.learning.springcloud.service;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，------------PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
