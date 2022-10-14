package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

/**
 * initializer class
 */
@EnableKafkaStreams
@SpringBootApplication
public class Initializer {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

}
