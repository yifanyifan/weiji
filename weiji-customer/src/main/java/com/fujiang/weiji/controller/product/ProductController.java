package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.feign.runoob.OrderFeign;
import com.fujiang.weiji.feign.runoob.ServiceFeign;
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
    //@Autowired
    private ServiceFeign serviceFeign;
    //@Autowired
    private OrderFeign orderFeign;

    @RequestMapping(value = "getConsumer", method = RequestMethod.GET)
    public String getConsumer() {
        return serviceFeign.getProduct();
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert() {
        return serviceFeign.insert();
    }

    @RequestMapping(value = "insertOrder", method = RequestMethod.GET)
    public String insertOrder() {
        return orderFeign.insert();
    }

}
