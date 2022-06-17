package io.github.augusto1217.config;

import io.github.augusto1217.anotations.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MyConfiguration {

    @Bean
    public CommandLineRunner execute(){
        return args -> {
            System.out.println("Execute configuration of development");
        };
    }
}
