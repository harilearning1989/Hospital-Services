package com.web.demo.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:common-service-${spring.profiles.active}.properties")
public record DataSourceProp(
        @Value("${mysql.jdbc.driverClassName}")
        String driverClass,
        @Value("${mysql.jdbc.url}")
        String url,
        @Value("${mysql.jdbc.username}")
        String username,
        @Value("${mysql.jdbc.password}")
        String password
) {
}
