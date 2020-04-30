package com.fujiang.weiji.feign.product;

import com.fujiang.weiji.feign.product.factory.ProductFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@FeignClient(name = "weiji-service", path = "service", fallbackFactory = ProductFallbackFactory.class)
@Component
public interface ProductService {
    @RequestMapping(value = "/product/getProduct")
    String getProduct();
}
