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
public class SpringCloudGatewayRouting {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudGatewayRouting.class);
    private AuthenticationFilter authenticationFilter;

    @Autowired
    public SpringCloudGatewayRouting setAuthenticationFilter(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
        return this;
    }

    /*@Bean
        public GlobalFilter globalFilter() {
            return (exchange, chain) -> {
                System.out.println("First Global filter");
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    System.out.println("Second Global filter");
                }));
            };
        }*/

    /*@Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }*/
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        LOGGER.info("Enters configureRoute::"+builder);
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
                                                "*"))
                                //.filters(f -> f.filter(authenticationFilter))
                                .uri("lb://PATIENT-SERVICE"))
                .route("doctor",
                        r -> r.path("/doctor/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://DOCTOR-SERVICE"))
                .route("report",
                        r -> r.path("/report/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://REPORT-SERVICE"))
                .route("admin",
                        r -> r.path("/admin/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://ADMIN-SERVICE"))
                .route("dental",
                        r -> r.path("/dental/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://DENTAL-SERVICE"))
                .route("general",
                        r -> r.path("/general/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://GENERAL-SERVICE"))
                .route("billing",
                        r -> r.path("/billing/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://BILLING-SERVICE"))
                .route("paymentId",
                        r -> r.path("/payment/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("http://localhost:9009")) //static routing
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
