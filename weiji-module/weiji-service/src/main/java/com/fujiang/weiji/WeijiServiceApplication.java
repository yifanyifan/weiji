package com.fujiang.weiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableEurekaClient适用Eureka注册中心 / @EnableDiscoveryClient可以是其他注册中心
@EnableEurekaClient
@EnableFeignClients
public class WeijiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeijiServiceApplication.class, args);
    }

}
