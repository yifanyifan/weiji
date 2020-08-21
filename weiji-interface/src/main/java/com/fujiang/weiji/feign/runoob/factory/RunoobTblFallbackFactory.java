package com.fujiang.weiji.feign.runoob.factory;

import com.fujiang.weiji.feign.runoob.RunoobTblServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
@Component
public class RunoobTblFallbackFactory implements RunoobTblServiceFeign {
    @Override
    public String getProduct() {
        return "getProduct失败";
    }

    @Override
    public String insert() {
        return "insert失败";
    }
}
