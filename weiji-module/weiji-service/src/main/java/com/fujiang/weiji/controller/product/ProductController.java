package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.entity.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public String getProduct() throws InterruptedException {
        //实现
        Product product = new Product();
        //Thread.sleep(3000);
        logger.info("============================>dddddddwer1111111111111111111111111111111111");

        return port + ":" + product.toString();
    }
}
