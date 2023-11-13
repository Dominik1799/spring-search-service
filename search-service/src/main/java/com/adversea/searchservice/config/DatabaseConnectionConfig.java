package com.adversea.searchservice.config;

import com.adversea.searchservice.repository.SearchRepositoryElastic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// this will kill the application after startup if the connection params are not correct
// it should crash in maximum of 30 seconds after application launch
// if it does not crash, the connection should be good to go
@Configuration
public class DatabaseConnectionConfig {
    @Bean
    public CommandLineRunner commandLineRunner(SearchRepositoryElastic repository ){
        return args -> {
            repository.findById("1");
        };
    }

}
