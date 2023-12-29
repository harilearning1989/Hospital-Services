package com.web.report.config.db;

import com.web.demo.prop.DatasourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

   /* @Autowired
    public DatabaseConfig setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }
    private Environment environment;*/

    private DatasourceProperties datasourceProperties;

    @Autowired
    public DatabaseConfig setDatasourceProperties(DatasourceProperties datasourceProperties) {
        this.datasourceProperties = datasourceProperties;
        return this;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(datasourceProperties.getDriverClass());
        dataSource.setUrl(datasourceProperties.getUrl());
        dataSource.setUsername(datasourceProperties.getUsername());
        dataSource.setPassword(datasourceProperties.getPassword());
        return dataSource;
    }
    /*@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getProperty("mysql.jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("mysql.jdbc.url"));
        dataSource.setUsername(environment.getProperty("mysql.jdbc.username"));
        dataSource.setPassword(environment.getProperty("mysql.jdbc.password"));

        return dataSource;
    }
*/
}
