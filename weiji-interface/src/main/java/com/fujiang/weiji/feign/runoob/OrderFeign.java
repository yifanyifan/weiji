package com.fujiang.weiji.feign.runoob;

import com.fujiang.weiji.feign.runoob.factory.OrderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@FeignClient(name = "weiji-order", path = "order", fallbackFactory = OrderFallbackFactory.class)
@Component
public interface OrderFeign {
    @RequestMapping(value = "/order/insert", method = RequestMethod.POST)
    String insert();
}
