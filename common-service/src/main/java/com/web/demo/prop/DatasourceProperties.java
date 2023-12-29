package com.web.demo.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:common-service-${spring.profiles.active}.properties")
@Data
public class DatasourceProperties {

    @Value("${mysql.jdbc.driverClassName}")
    private String driverClass;
    @Value("${mysql.jdbc.url}")
    private String url;
    @Value("${mysql.jdbc.username}")
    private String username;
    @Value("${mysql.jdbc.password}")
    private String password;

}
