package com.web.demo.config.db;

import com.web.demo.prop.DataSourceProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class HospitalManagementConfig {
    private DataSourceProp dataSourceProp;

    @Autowired
    public HospitalManagementConfig setDataSourceProp(DataSourceProp dataSourceProp) {
        this.dataSourceProp = dataSourceProp;
        return this;
    }

    @Bean
    public DataSource dataSource() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("spring.datasource.jpa.format-sql", "true");
        properties.put("spring.datasource.jpa.show-sql", "true");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProp.driverClass());
        dataSource.setUrl(dataSourceProp.url());
        dataSource.setUsername(dataSourceProp.username());
        dataSource.setPassword(dataSourceProp.password());
        dataSource.setConnectionProperties(properties);
        return dataSource;
    }
}
