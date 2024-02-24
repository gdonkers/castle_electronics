package com.castle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext spring = SpringApplication.run(Application.class, args);

        String port = spring.getEnvironment().getProperty("server.port");
        log.info("Swagger available on http://localhost:{}/swagger-ui.html", port);
    }

}
