package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import com.fujiang.weiji.feign.product.ProductService;
import com.fujiang.weiji.feign.runoob.RunoobTblService;
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
    private ProductService productService;
    @Autowired
    private RunoobTblService runoobTblService;

    @RequestMapping(value = "getConsumer")
    public String getConsumer() {
        return productService.getProduct();
    }

    @RequestMapping(value = "insert")
    public String insert() {
        runoobTblService.insert(new RunoobTbl());
        return "";
    }
}
