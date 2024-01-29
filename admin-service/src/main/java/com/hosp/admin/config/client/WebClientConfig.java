package com.hosp.admin.config.client;

import com.hosp.admin.services.client.CreateUserClientService;
import com.hosp.admin.services.client.EmployeeClientService;
import com.web.demo.config.client.HospitalWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${login.rest.url}")
    private String loginUrl;
    @Value("${login.rest.localUrl}")
    private String loginLocalUrl;
    @Value("${patient.header.key}")
    private String headerKey;
    @Value("${patient.header.value}")
    private String headerValue;
    @Value("${emp.rest.url}")
    private String empUrl;

    @Bean
    @LoadBalanced
    public CreateUserClientService adminClientService() {
        Map<String, String> headersMap = new HashMap<>();
        //headersMap.put(headerKey,headerValue);
        return new HospitalWebClient()
                .createClient(CreateUserClientService.class, loginLocalUrl, headersMap);
    }

    @Bean
    public EmployeeClientService employeeClientService() {
        LOGGER.info("employeeClientService");
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put(headerKey, headerValue);

        return new HospitalWebClient()
                .createClient(EmployeeClientService.class, empUrl, headersMap);
    }

    /*@Value("${patient.rest.url}")
    private String patientUrl;
    @Value("${patient.rest.urlLocal}")
    private String patientUrlLocal;
    @Value("${doctor.rest.url}")
    private String doctorUrl;
    @Value("${doctor.rest.urlLocal}")
    private String doctorUrlLocal;*/
    /*@Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

    /*@Bean
    @LoadBalanced
    WebClient webClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .baseUrl(patientUrl)
                //.filter(withRetryableRequests())
                .build();
    }
    @Bean
    PatientClientTempService createWebClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(PatientClientTempService.class);
    }*/

    /*WebClient webClient = WebClient.builder()
                .baseUrl(patientUrl)
                .defaultStatusHandler(
                        httpStatusCode -> HttpStatus.NOT_FOUND == httpStatusCode,
                        response -> Mono.empty())
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new ExternalCommunicationException(response.statusCode().value())))
                .build();*/



    /*private ExchangeFilterFunction withRetryableRequests() {
        return (request, next) -> next.exchange(request)
                .flatMap(clientResponse -> Mono.just(clientResponse)
                        .filter(response -> clientResponse.statusCode().isError())
                        .flatMap(response -> clientResponse.createException())
                        .flatMap(Mono::error)
                        .thenReturn(clientResponse))
                .retryWhen(this.retryBackoffSpec());
    }

    private RetryBackoffSpec retryBackoffSpec() {
        return Retry.backoff(3, Duration.ofSeconds(2))
                .filter(throwable -> throwable instanceof WebClientResponseException) // here filter on the errors for which you want a retry
                .doBeforeRetry(retrySignal ->
                        LOGGER.warn("Retrying request after following exception : {}",
                                retrySignal.failure().getLocalizedMessage()))
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure());
    }*/

}
