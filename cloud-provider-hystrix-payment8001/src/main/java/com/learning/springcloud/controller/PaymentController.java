package com.learning.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import com.learning.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/timeout")
    public CommonResult<String> timeout() {
        int time = 5000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<>(200, "timeout(ms)：" + time);
    }

    @GetMapping(value = "/payment/ok")
    @HystrixCommand(fallbackMethod = "fallback")
    public CommonResult<Payment> ok() {
        return new CommonResult<>(200, "success");
    }

    /**
     * 服务熔断示例
     */
    @GetMapping(value = "/payment/get/{id}")
    public String get(@PathVariable Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }
}
