package com.web.demo.filter;

import com.web.demo.utils.JwtUtil;
import com.web.demo.validator.RouterValidator;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationFilter(RouterValidator routerValidator, JwtUtil jwtUtil) {
        this.routerValidator = routerValidator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        LOGGER.info("========entering into AuthenticationFilter filter=====");
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                //"Authorization header is missing in request",
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            final String token = this.getAuthHeader(request);
            LOGGER.info("========entering into AuthenticationFilter filter token::"+token);
            if (!jwtUtil.validateJwtToken(token)) {
                //return this.onError(exchange, HttpStatus.FORBIDDEN);
                //"Authorization header is invalid",
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            this.updateRequest(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        String authorization = request.getHeaders().getOrEmpty("Authorization").get(0);
        return parseJwt(authorization);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void updateRequest(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("email", String.valueOf(claims.get("email")))
                .header("username", String.valueOf(claims.get("username")))
                .header("iss", String.valueOf(claims.get("iss")))
                .build();
    }

    private String parseJwt(String authorization) {
        //if (StringUtils.isNoneBlank(authorization) && authorization.startsWith("Bearer ")) {
        return authorization.substring(7);
        //}
        //return null;
    }
}
