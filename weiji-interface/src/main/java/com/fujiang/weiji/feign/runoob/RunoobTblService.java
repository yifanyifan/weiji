package com.fujiang.weiji.feign.runoob;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import com.fujiang.weiji.feign.runoob.factory.RunoobTblFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@FeignClient(name = "weiji-service", path = "service", fallbackFactory = RunoobTblFallbackFactory.class)
@Component
public interface RunoobTblService {
    @RequestMapping(value = "/insert")
    void insert(RunoobTbl runoobTbl);
}
