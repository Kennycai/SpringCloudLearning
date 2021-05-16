package com.learning.RibbonRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡策略
 * 不能放在componentScan所在的包下，不然的话所有server都会采用当前策略，就起不到单独定制的作用
 */
@Configuration
public class MyRule {
    @Bean
    public IRule RandomRole() {
        return new RandomRule();
    }
}
