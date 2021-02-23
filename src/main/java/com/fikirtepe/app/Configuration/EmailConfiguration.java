package com.fikirtepe.app.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {
    @Bean(name = "email")
    public String getEmail(){
        return "noreplyfikirtepe@gmail.com";
    }
}
