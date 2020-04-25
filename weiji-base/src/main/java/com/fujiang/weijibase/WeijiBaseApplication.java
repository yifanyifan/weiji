package com.fujiang.weijibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WeijiBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeijiBaseApplication.class, args);
	}

}
