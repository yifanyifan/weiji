package com.fujiang.weiji.feign.runnoob.factory;

import com.fujiang.weiji.feign.runnoob.RunoobTblServiceFeign1;
import feign.hystrix.FallbackFactory;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
/*@Component*/
public class RunoobTblFallbackFactory1 implements FallbackFactory<RunoobTblServiceFeign1> {
    @Override
    public RunoobTblServiceFeign1 create(Throwable throwable) {
        return new RunoobTblServiceFeign1() {
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