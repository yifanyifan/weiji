package com.fujiang.weiji.feign.runoob.factory;

import com.fujiang.weiji.feign.runoob.OrderFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
@Component
public class OrderFallbackFactory implements FallbackFactory<OrderFeign> {
    @Override
    public OrderFeign create(Throwable throwable) {
        return new OrderFeign() {
            @Override
            public String insert() {
                return "insert订单失败";
            }
        };
    }
}