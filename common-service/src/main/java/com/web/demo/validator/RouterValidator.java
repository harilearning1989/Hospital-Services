package com.web.demo.validator;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/patient/register",
            "/doctor/register",
            "/admin/register"
    );

    public Predicate<String> isSecured =
            path -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> path.contains(uri));

}