package com.mastery.java.task;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI().info(apiInfo());
    }

    public Info apiInfo() {
        Info info = new Info();
        info
                .title("Simplewebapp API")
                .description("API for manage of employees")
                .version("v1.0.0");
        return info;
    }
}