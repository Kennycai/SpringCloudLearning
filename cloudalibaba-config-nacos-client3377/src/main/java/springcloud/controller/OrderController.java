package springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   //SpringCloud原生注解 支持Nacos的动态刷新功能
public class OrderController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String get() {
        return configInfo;
    }
}
