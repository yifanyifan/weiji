package com.fujiang.weiji.controller.product;

import com.alibaba.fastjson.JSON;
import com.fujiang.weiji.entity.product.Product;
import com.fujiang.weiji.rabbitmq.config.RabbitmqEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public String getProduct() throws InterruptedException {
        //实现
        Product product = new Product();
        Thread.sleep(20000);
        logger.info("============================>dddddddwer1111111111111111111111111111111111");

        return port + ":" + JSON.toJSONString(product) + ":XXX";
    }

    @RequestMapping(value = "/sendFinanceMessage", method = RequestMethod.GET)
    public String sendFinanceMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(RabbitmqEnum.EXCHANGE_FINANCE.getCode(), RabbitmqEnum.ROUTKEY_FINANCE_EXPORT.getCode(), map);
        return "ok";
    }

    @RequestMapping(value = "/sendOrderMessage", method = RequestMethod.GET)
    public String sendOrderMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(RabbitmqEnum.EXCHANGE_FINANCE.getCode(), RabbitmqEnum.ROUTKEY_ORDER_EXPORT.getCode(), map);
        return "ok";
    }

    @RequestMapping(value = "/sendDealyMessage", method = RequestMethod.GET)
    public String sendDealyMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(RabbitmqEnum.EXCHANGE_FINANCE.getCode(), RabbitmqEnum.QUEUE_DELAY.getCode(), map);
        System.out.println(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return "ok";
    }
}
