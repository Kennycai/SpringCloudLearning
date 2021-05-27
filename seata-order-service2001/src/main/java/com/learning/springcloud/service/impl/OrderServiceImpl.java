package com.learning.springcloud.service.impl;

import com.learning.springcloud.dao.OrderDao;
import com.learning.springcloud.domain.Order;
import com.learning.springcloud.service.AccountService;
import com.learning.springcloud.service.OrderService;
import com.learning.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说:
     * 下订单->减库存->减余额->改状态
     * GlobalTransactional seata开启分布式事务,异常时回滚,name保证唯一即可
     */
    @Override
    // 添加 @Transactional 只会回滚当前服务的数据
//    @Transactional(rollbackFor = Exception.class)
    // 添加 @GlobalTransactional 会回滚所有数据
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("----->开始创建新订单");
        //1 新建订单
        orderDao.createOrder(order);

        log.info("----->订单微服务开始调用库存，做扣减Count");
        //2 扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        log.info("----->订单微服务开始调用账户，做扣减Money");
        //3 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //修改订单状态，从0到1，1代表以及完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("----->修改订单状态结束");
//        throw new RuntimeException("测试回滚");
        log.info("----->下订单结束了，O(∩_∩)O哈哈~");
    }
}
