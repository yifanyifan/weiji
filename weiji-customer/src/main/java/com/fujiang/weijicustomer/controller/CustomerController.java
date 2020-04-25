package com.fujiang.weijicustomer.controller;

import com.fujiang.weijicustomer.service.ProductService;
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
public class CustomerController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "getConsumer")
    public String getConsumer(){
        return productService.getProduct();
    }
}
