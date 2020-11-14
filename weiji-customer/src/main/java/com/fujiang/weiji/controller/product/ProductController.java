package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.feign.runoob.OrderServiceFeign;
import com.fujiang.weiji.feign.runoob.RunoobTblServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private RunoobTblServiceFeign runoobTblServiceFeign;
    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @RequestMapping(value = "getConsumer", method = RequestMethod.GET)
    public String getConsumer() {
        return runoobTblServiceFeign.getProduct();
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert() {
        return runoobTblServiceFeign.insert();
    }

    @RequestMapping(value = "insertOrder", method = RequestMethod.GET)
    public String insertOrder() {
        return orderServiceFeign.insert();
    }

}
