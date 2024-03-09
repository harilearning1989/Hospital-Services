package com.web.demo.config;

//@Configuration
public class SpringCloudGatewayRouting {

   /* private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudGatewayRouting.class);
    private AuthenticationFilter authenticationFilter;

    @Autowired
    public SpringCloudGatewayRouting setAuthenticationFilter(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
        return this;
    }*/

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
    /*@Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        LOGGER.info("Enters configureRoute::" + builder);
        return builder.routes()
                .route("login", r -> r.path("/auth/**")
                        .filters(f ->
                                f.setResponseHeader("Access-Control-Allow-Origin",
                                        "*"))
                        .uri("lb://LOGIN-SERVICE"))
                //.uri("http://localhost:8081")) //static routing
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
    }*/

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
