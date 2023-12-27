package com.web.demo.config;

import com.web.demo.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class SpringCloudGatewayRouting {

    @Autowired
    private AuthenticationFilter filter;

    /*@Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            System.out.println("First Global filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Second Global filter");
            }));
        };
    }*/
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("login", r -> r.path("/auth/**").uri("lb://LOGIN-SERVICE"))
                .route("patient", r -> r.path("/patient/**").uri("lb://PATIENT-SERVICE"))
                .route("dental", r -> r.path("/dental/**").uri("lb://DENTAL-SERVICE"))
                .route("general", r -> r.path("/general/**").uri("lb://GENERAL-SERVICE"))
                .route("billing",
                        r -> r.path("/billing/**")
                                .filters(f -> f.filter(filter))
                                .uri("lb://BILLING-SERVICE"))
                .route("paymentId", r -> r.path("/payment/**").uri("http://localhost:9009")) //static routing
                .build();
    }

   /* @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("employeeModule", r -> r.path("/employee/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                .addResponseHeader("first-response", "first-response-header"))
                        .uri("http://localhost:8081/")
                )

                .route("consumerModule", r -> r.path("/consumer/**")
                        //Pre and Post Filters provided by Spring Cloud Gateway
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082/")
                )
                .build();
    }*/
}
