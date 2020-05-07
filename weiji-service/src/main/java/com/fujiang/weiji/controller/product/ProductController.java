package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.entity.product.Product;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${from}")
    private String value;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public String getProduct() throws InterruptedException {
        //实现
        Product product = new Product();
        Thread.sleep(4000);
        return port + ":" + value + product.toString();
    }
}
