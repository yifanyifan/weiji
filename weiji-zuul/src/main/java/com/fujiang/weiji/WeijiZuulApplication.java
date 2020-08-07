package com.fujiang.weiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class WeijiZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeijiZuulApplication.class, args);
    }

}
