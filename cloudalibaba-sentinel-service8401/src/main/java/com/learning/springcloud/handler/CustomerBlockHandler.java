package com.learning.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.learning.springcloud.entities.CommonResult;
import com.learning.springcloud.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按客户自定义，global handlerException-----1");
    }
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"按客户自定义，global handlerException-----2");
    }
}
