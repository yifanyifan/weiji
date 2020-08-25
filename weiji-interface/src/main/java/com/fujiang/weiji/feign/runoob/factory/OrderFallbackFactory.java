package com.fujiang.weiji.feign.runoob.factory;

import com.fujiang.weiji.feign.runoob.OrderServiceFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
@Component
public class OrderFallbackFactory implements FallbackFactory<OrderServiceFeign> {
    @Override
    public OrderServiceFeign create(Throwable throwable) {
        return new OrderServiceFeign() {
            @Override
            public String insert() {
                return "insert订单失败";
            }
        };
    }
}