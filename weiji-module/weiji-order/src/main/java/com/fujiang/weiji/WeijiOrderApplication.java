package com.fujiang.weiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//@EnableEurekaClient适用Eureka注册中心 / @EnableDiscoveryClient可以是其他注册中心
/*@EnableEurekaClient
@EnableFeignClients*/
public class WeijiOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeijiOrderApplication.class, args);
    }

}
