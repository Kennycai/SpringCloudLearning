package com.learning.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.learning.springcloud.handler.BlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "--------testB";
    }

    /**
     * 降级：异常数测试
     *
     * @return
     */
    @GetMapping("/testE")
    public String testE() {
        log.info("testE测试异常数");
        int age = 10 / 0;
        return "--------testE 测试异常数";
    }

    /**
     * 热点：流控测试，在sentinel dashboard中配置热点流控规则，测试当前接口
     * SentinelResource 配置，服务降级兜底方法
     */
    @GetMapping("/testHotKey")
//    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    @SentinelResource(value = "testHotKey", blockHandlerClass = BlockHandler.class,
            blockHandler = "dealTestHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p1", required = false) String p2) {
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "---------------deal_testHotKey,o(╥﹏╥)o";
    }
}
