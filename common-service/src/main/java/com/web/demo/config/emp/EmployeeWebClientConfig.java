package com.web.demo.config.emp;

import com.web.demo.config.client.HospitalWebClient;
import com.web.demo.services.EmployeeClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class EmployeeWebClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWebClientConfig.class);

    @Value("${emp.rest.url}")
    private String employeeUrl;
    @Value("${emp.rest.header.key}")
    private String headerKey;
    @Value("${emp.rest.header.value}")
    private String headerValue;

    @Bean
    public EmployeeClientService employeeClientService(){
        LOGGER.info("employeeClientService");
        Map<String,String> headersMap = new HashMap<>();
        headersMap.put(headerKey,headerValue);

        return new HospitalWebClient()
                .createClient(EmployeeClientService.class,employeeUrl,headersMap);
    }


}
