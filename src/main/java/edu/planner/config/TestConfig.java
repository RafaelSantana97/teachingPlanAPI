package edu.planner.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

    private final DBService dbService;

    @Bean
    public void instantiateDatabase() {
        dbService.instantiateEssentialData();
        dbService.instantiateTestData();
    }
}