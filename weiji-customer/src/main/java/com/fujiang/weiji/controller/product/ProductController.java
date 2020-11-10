package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.feign.runoob.OrderServiceFeign;
import com.fujiang.weiji.feign.runoob.RunoobTblServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@RestController
public class ProductController {
    @Autowired
    private RunoobTblServiceFeign runoobTblServiceFeign;
    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @RequestMapping(value = "getConsumer")
    public String getConsumer() {
        return runoobTblServiceFeign.getProduct();
    }

    @RequestMapping(value = "insert")
    public String insert() {
        return runoobTblServiceFeign.insert();
    }

    @RequestMapping(value = "insertOrder")
    public String insertOrder() {
        return orderServiceFeign.insert();
    }

}
