package com.learning.springcloud.controller;

import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;
import com.learning.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
// hystrix 默认 fallback
@DefaultProperties(defaultFallback = "fallback")
public class OrderController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/timeout")
//    @HystrixCommand(fallbackMethod = "fallback",
            //设置这个线程的超时时间是3s，3s内是正常的业务逻辑，超过3s调用fallbackMethod指定的方法进行处理
//            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @HystrixCommand
    public CommonResult<String> timeout() {
//        int a = 10/0;
        return paymentService.timeout();
    }

    @GetMapping("/consumer/payment/ok")
    public CommonResult<Payment> ok() {
        return paymentService.ok();
    }

    public CommonResult<String> fallback() {
        return new CommonResult<>(500, "消费侧fallback");
    }
}
