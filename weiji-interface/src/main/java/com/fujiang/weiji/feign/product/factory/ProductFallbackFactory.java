package com.fujiang.weiji.feign.product.factory;

import com.fujiang.weiji.feign.product.ProductService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/27
 * Modified By:
 */
@Component
public class ProductFallbackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public String getProduct() {
                return "111111111111111111";
            }
        };
    }
}
