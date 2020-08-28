package com.fujiang.weiji.service.product.impl;

import com.fujiang.weiji.feign.runoob.OrderServiceFeign;
import com.fujiang.weiji.feign.runoob.RunoobTblServiceFeign;
import com.fujiang.weiji.service.product.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RunoobTblServiceFeign runoobTblServiceFeign;
    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @Override
    @GlobalTransactional(name = "weiji_group_sample", rollbackFor = Exception.class)
    public String insertAll2() {
        String s1 = runoobTblServiceFeign.insert();
        String s2 = orderServiceFeign.insert();
        return s1 + ":" + s2;
    }
}
