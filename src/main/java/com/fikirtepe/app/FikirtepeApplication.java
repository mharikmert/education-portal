package com.fikirtepe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FikirtepeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FikirtepeApplication.class, args);
    }

}
