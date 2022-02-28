package com.example.provasuficienciaprogramacaoweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EntityScan(basePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example")
@EnableSwagger2
public class ProvaSuficienciaProgramacaoWeb2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProvaSuficienciaProgramacaoWeb2Application.class, args);
    }

}
