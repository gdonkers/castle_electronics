package com.castle.cucumber;

import com.castle.service.model.IdGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = "com.castle.service")
@EnableAutoConfiguration
public class TestConfig {

    @Bean
    @Primary
    IdGenerator testIdGenerator() {
        return mock(IdGenerator.class);
    }
}
