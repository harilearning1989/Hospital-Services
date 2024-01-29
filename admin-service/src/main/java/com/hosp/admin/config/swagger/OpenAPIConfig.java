package com.hosp.admin.config.swagger;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${login.rest.url}")
    private String loginUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(loginUrl);
        devServer.setDescription("Server URL in Development environment");

        /*Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");*/

        Contact contact = new Contact();
        contact.setEmail("hari@gmail.com");
        contact.setName("Hari");
        contact.setUrl("https://www.education.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.education.com/terms")
                .license(mitLicense);

        //return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
