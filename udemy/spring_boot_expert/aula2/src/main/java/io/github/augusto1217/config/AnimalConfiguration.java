package io.github.augusto1217.config;

import io.github.augusto1217.interfaces.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean(name="cachorro")
    public Animal cachorro() {
        return new Animal() {
            @Override
            public void makeNoise() {
                System.out.println("Auuu, Auuuu");
            }
        };
    }

    @Bean
    public Animal gato() {
        return new Animal() {
            @Override
            public void makeNoise() {
                System.out.println("Miauuu, Miauuuu");
            }
        };
    }
}
