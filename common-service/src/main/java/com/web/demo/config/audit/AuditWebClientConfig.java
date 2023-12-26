package com.web.demo.config.audit;

import com.web.demo.config.client.HospitalWebClient;
import com.web.demo.services.AuditClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuditWebClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditWebClientConfig.class);

    @Value("${audit.api.host.baseurl}")
    private String auditBaseUrl;
    @Value("${emp.rest.url}")
    private String employeeUrl;
    @Value("${emp.rest.header.key}")
    private String headerKey;
    @Value("${emp.rest.header.value}")
    private String headerValue;

    @Bean
    public AuditClientService auditClientService() {
        LOGGER.info("AuditWebClientConfig auditClientService entry");
        Map<String,String> headersMap = new HashMap<>();
        headersMap.put(headerKey,headerValue);
        return new HospitalWebClient()
                .createAuditClient(AuditClientService.class, auditBaseUrl,headersMap);
    }
}
