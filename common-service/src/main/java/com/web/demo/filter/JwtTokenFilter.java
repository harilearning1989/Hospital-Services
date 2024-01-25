package com.web.demo.filter;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.web.demo.constants.Constants;
import com.web.demo.utils.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    //private static final String[] excludedEndpoints = new String[]{"/auth/signing, */bar/**"};
    @Value("${hosp.filter.url.ignore}")
    private List<String> excludedEndpoints;

    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public JwtTokenFilter setJwtTokenUtils(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
        return this;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        /*Map<String, List<String>> headersMap = Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h))
                ));
        headersMap.forEach((k, v) -> {
            v.forEach(f -> {
                System.out.println("Key::" + k + "===Value::" + f);
            });
        });
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String authHeaderTemp = request.getHeader("authorization");
        LOGGER.info("JwtTokenFilter enters into doFilterInternal authHeaderTemp::" + authHeaderTemp);
        if (StringUtils.isEmpty(authHeader) || (!StringUtils.startsWith(authHeader, Constants.BEARER_PREFIX)
                && !StringUtils.startsWith(authHeader, Constants.BASIC_PREFIX))) {
            LOGGER.info("JwtTokenFilter enters into authHeader condition");
            jwtTokenUtils.generateErrorMesage(response, Constants.TOKEN_NOT_FOUND, HttpStatus.FORBIDDEN.value());
            return;
        }

        if (StringUtils.startsWith(authHeader, Constants.BEARER_PREFIX)) {
            final String jwtToken = authHeader.split(" ")[1].trim();
            LOGGER.info("JwtTokenFilter enters into BEARER_PREFIX condition jwtToken::" + jwtToken);
            try {
                jwtTokenUtils.validateJwtToken(jwtToken);
            } catch (Exception e) {
                LOGGER.info("Exception message::" + e.getMessage());
                jwtTokenUtils.generateErrorMesage(response, Constants.TOKEN_VALIDATION_FAILED, HttpStatus.UNAUTHORIZED.value());
                return;
            }
        } else {
            LOGGER.info(Constants.BEARER_PREFIX_NOT_FOUND);
            jwtTokenUtils.generateErrorMesage(response, Constants.BEARER_PREFIX_NOT_FOUND, HttpStatus.FORBIDDEN.value());
            return;
        }*/
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        LOGGER.info("JwtTokenFilter enters into shouldNotFilter requestUri::"
                + request.getServletPath() + "====excludedEndpoints::" + excludedEndpoints);
        return excludedEndpoints.stream()
                .anyMatch(s -> s.contains(request.getServletPath()));
    }

}
