package com.fujiang.weijiservice.controller;

import product.entity.Product;
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
    @RequestMapping(value = "getProduct")
    public String getProduct() {
        Product product = new Product();
        return product.toString();
    }
}
