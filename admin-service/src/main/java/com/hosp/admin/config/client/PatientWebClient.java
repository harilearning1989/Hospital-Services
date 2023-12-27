package com.hosp.admin.config.client;

import com.hosp.admin.services.client.PatientClientService;
import com.web.demo.config.client.HospitalWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PatientWebClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.web.demo.config.emp.EmployeeWebClientConfig.class);

    @Value("${patient.rest.url}")
    private String patientUrl;
    @Value("${patient.header.key}")
    private String headerKey;
    @Value("${patient.header.value}")
    private String headerValue;

    @Bean
    public PatientClientService patientClientService(){
        LOGGER.info("employeeClientService");
        Map<String,String> headersMap = new HashMap<>();
        headersMap.put(headerKey,headerValue);

        return new HospitalWebClient()
                .createClient(PatientClientService.class,patientUrl,headersMap);
    }


}

