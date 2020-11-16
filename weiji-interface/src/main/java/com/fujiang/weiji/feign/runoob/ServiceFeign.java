package com.fujiang.weiji.feign.runoob;

import com.fujiang.weiji.feign.runoob.factory.RunoobTblFallbackFactory;
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
@FeignClient(name = "weiji-service", path = "service", fallbackFactory = RunoobTblFallbackFactory.class)
@Component
public interface ServiceFeign {
    @RequestMapping(value = "/product/getProduct")
    String getProduct();

    @RequestMapping(value = "/runoobTbl/insert", method = RequestMethod.POST)
    String insert();
}
