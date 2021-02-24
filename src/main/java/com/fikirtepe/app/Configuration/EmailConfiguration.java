package com.fikirtepe.app.Configuration;

import com.fikirtepe.app.Service.EmailServiceImplementation.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

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
        return "noreplyfikirtepe@gmail.com";
    }

    @Bean(name = "email_password")
    public String getPassword(){
        return properties.getProperty("EMAIL_PASSWORD");
    }
}
