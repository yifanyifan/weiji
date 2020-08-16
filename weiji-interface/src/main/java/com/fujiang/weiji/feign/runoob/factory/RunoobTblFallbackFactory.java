package com.fujiang.weiji.feign.runoob.factory;

import com.fujiang.weiji.feign.runoob.RunoobTblServiceFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
@Component
public class RunoobTblFallbackFactory implements FallbackFactory<RunoobTblServiceFeign> {
    @Override
    public RunoobTblServiceFeign create(Throwable throwable) {
        return new RunoobTblServiceFeign() {
            @Override
            public String getProduct() {
                return "getProduct失败";
            }

            @Override
            public String insert() {
                return "insert失败";
            }
        };
    }
}
