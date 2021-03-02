package com.fujiang.weiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WeijiAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeijiAuthApplication.class, args);
    }
}
