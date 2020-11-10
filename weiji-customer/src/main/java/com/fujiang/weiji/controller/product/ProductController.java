package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.feign.runoob.OrderServiceFeign;
import com.fujiang.weiji.feign.runoob.RunoobTblServiceFeign;
import com.fujiang.weiji.service.product.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
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
    @Autowired
    private ProductService productService;

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

    @RequestMapping(value = "insertAll")
    @GlobalTransactional(name = "weiji_seata2222222222222222222", rollbackFor = Exception.class)
    public String insertAll() {
        String str = RootContext.getXID();

        String s1 = runoobTblServiceFeign.insert();
        String s2 = orderServiceFeign.insert();
        return s1 + ":" + s2;
    }

    @RequestMapping(value = "insertAll2")
    public String insertAll2() {
        return productService.insertAll2();
    }
}
