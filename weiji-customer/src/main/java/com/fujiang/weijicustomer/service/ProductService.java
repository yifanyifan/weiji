package com.fujiang.weijicustomer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@FeignClient(name = "weiji-service", path = "service")
@Component
public interface ProductService {
    @RequestMapping(value = "getProduct")
    String getProduct();
}
