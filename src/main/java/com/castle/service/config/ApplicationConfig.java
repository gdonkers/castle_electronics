package com.castle.service.config;

import com.castle.service.model.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ApplicationConfig {
    @Bean
    IdGenerator entityIdGenerator() {
        return () -> UUID.randomUUID().toString();
    }
}
