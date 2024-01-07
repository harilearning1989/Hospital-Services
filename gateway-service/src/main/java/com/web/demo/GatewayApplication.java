package com.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	//https://github.com/rajithd/spring-cloud-security-jwt/blob/main/gateway-service/src/main/java/com/rd/spring/gateway/config/JwtUtil.java
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
