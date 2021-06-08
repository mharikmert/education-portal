package com.fikirtepe.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class EmailConfiguration {
    private final Properties properties = new Properties();

    public EmailConfiguration() throws  IOException{
        this.properties.load(configFile());
    }

    public FileInputStream configFile(){
        try{
            return new FileInputStream("src/main/resources/config.properties");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    @Bean(name = "email")
    public String getEmail(){
        return properties.getProperty("EMAIL");
    }

    @Bean(name = "email_password")
    public String getPassword(){
        return properties.getProperty("EMAIL_PASSWORD");
    }
}
