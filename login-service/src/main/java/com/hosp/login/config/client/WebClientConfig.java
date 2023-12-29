package com.hosp.login.config.client;

import com.hosp.login.services.client.EmployeeClientService;
import com.web.demo.config.client.HospitalWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${emp.header.key}")
    private String headerKey;
    @Value("${emp.header.value}")
    private String headerValue;
    @Value("${emp.rest.url}")
    private String empUrl;

    @Bean
    public EmployeeClientService employeeClientService() {
        LOGGER.info("employeeClientService");
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put(headerKey, headerValue);

        return new HospitalWebClient()
                .createClient(EmployeeClientService.class, empUrl, headersMap);
    }

}
