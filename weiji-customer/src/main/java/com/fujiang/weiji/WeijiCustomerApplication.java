package com.fujiang.weiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*@EnableEurekaClient*/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WeijiCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeijiCustomerApplication.class, args);
    }

}
