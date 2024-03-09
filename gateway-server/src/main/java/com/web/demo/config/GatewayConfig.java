package com.web.demo.config;

import com.web.demo.filter.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudGatewayRouting.class);
    private AuthenticationFilter authenticationFilter;

    @Autowired
    public GatewayConfig setAuthenticationFilter(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
        return this;
    }

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        LOGGER.info("Enters configureRoute::" + builder);
        return builder.routes()
                .route("login", r -> r.path("/auth/**")
                        .filters(f ->
                                f.setResponseHeader("Access-Control-Allow-Origin",
                                        "*"))
                        .uri("lb://LOGIN-SERVICE"))
                .route("patient",
                        r -> r.path("/patient/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "PATIENT-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://PATIENT-SERVICE"))
                .route("doctor",
                        r -> r.path("/doctor/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "DOCTOR-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://DOCTOR-SERVICE"))
                .route("report",
                        r -> r.path("/report/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "REPORT-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://REPORT-SERVICE"))
                .route("admin",
                        r -> r.path("/admin/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "ADMIN-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://ADMIN-SERVICE"))
                .route("dental",
                        r -> r.path("/dental/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "DENTAL-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://DENTAL-SERVICE"))
                .route("general",
                        r -> r.path("/general/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "GENERAL-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://GENERAL-SERVICE"))
                .route("billing",
                        r -> r.path("/billing/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "BILLING-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://BILLING-SERVICE"))
                .route("appointment",
                        r -> r.path("/appointment/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "APPOINTMENT-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("lb://APPOINTMENT-SERVICE"))
                .route("paymentId",
                        r -> r.path("/payment/**")
                                .filters(f ->
                                        f.setResponseHeader("Access-Control-Allow-Origin",
                                                        "*")
                                                .setRequestHeader("Service", "PAYMENT-SERVICE")
                                                .filter(authenticationFilter))
                                .uri("http://localhost:9009")) //static routing
                .build();
    }
}
