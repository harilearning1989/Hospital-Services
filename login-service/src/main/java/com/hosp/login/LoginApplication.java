package com.hosp.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
		"com.web.demo.*",
		"com.hosp.login.*"
})
@PropertySource("classpath:common-service-${spring.profiles.active}.properties")
//@PropertySource("common-service.properties")
//@PropertySource("classpath:login-service-${spring.profiles.active}.properties")
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
