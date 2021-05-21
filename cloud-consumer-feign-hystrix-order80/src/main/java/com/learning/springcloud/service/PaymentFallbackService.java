package com.learning.springcloud.service;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> ok() {
        return new CommonResult<>(500, "出错啦");
    }

    @Override
    public CommonResult<String> timeout() {
        return new CommonResult<>(500, "出错啦");
    }
}
