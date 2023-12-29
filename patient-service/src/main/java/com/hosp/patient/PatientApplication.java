package com.hosp.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
		"com.web.demo.*",
		"com.hosp.patient.*"
})
@PropertySource("classpath:common-service-${spring.profiles.active}.properties")
public class PatientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	  /*View prescription details
	  View medication from doctor
	  View doctor list
	  View blood bank status,Test Reports
	  View operation history
	  View admit history. like bed, ward icu etc*/
	}
}
