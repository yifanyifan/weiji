package com.fujiang.weiji.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitMQ 交换器/队列 声明及绑定
 */
@Configuration
public class DirectRabbitConfig {
    /**
     * 财务模块
     */
    @Bean
    DirectExchange financeDirectExchange() {
        //Direct交换机 起名：EXCHANGE_FINANCE
        return new DirectExchange(RabbitmqEnum.EXCHANGE_FINANCE.getCode(), true, false);
    }

    @Bean
    public Queue financeDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("TestDirectQueue",true,true,false);
        // 一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(RabbitmqEnum.QUEUE_FINANCE_EXPORT.getCode(), true, false, false);
    }

    @Bean
    Binding financeBindingDirect() {
        //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
        return BindingBuilder.bind(financeDirectQueue()).to(financeDirectExchange()).with(RabbitmqEnum.ROUTKEY_FINANCE_EXPORT.getCode());
    }

    /**
     * 订单模块
     */
    @Bean
    public Queue orderDirectQueue() {
        return new Queue(RabbitmqEnum.QUEUE_ORDER_EXPORT.getCode(), true, false, false);
    }

    @Bean
    Binding orderBindingDirect() {
        return BindingBuilder.bind(orderDirectQueue()).to(financeDirectExchange()).with(RabbitmqEnum.ROUTKEY_ORDER_EXPORT.getCode());
    }

    /**
     * 创建一个延时队列
     */
    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", RabbitmqEnum.EXCHANGE_FINANCE.getCode());
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", RabbitmqEnum.ROUTKEY_FINANCE_EXPORT.getCode());
        params.put("x-message-ttl", 10000);
        return new Queue(RabbitmqEnum.QUEUE_DELAY.getCode(), true, false, false, params);
    }

    @Bean
    Binding delayBindingDirect() {
        return BindingBuilder.bind(delayQueue()).to(financeDirectExchange()).with(RabbitmqEnum.QUEUE_DELAY.getCode());
    }

}
