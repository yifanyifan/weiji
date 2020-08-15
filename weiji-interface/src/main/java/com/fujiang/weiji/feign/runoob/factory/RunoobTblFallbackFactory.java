package com.fujiang.weiji.feign.runoob.factory;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import com.fujiang.weiji.feign.runoob.RunoobTblService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
@Component
public class RunoobTblFallbackFactory implements FallbackFactory<RunoobTblService> {
    @Override
    public RunoobTblService create(Throwable throwable) {
        return new RunoobTblService() {
            @Override
            public void insert(RunoobTbl runoobTbl) {

            }
        };
    }
}
