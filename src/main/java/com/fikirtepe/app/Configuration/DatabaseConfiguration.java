package com.fikirtepe.app.Configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Component
public class DatabaseConfiguration {

    private final Properties properties = new Properties();

    public FileInputStream configFile(){
        try{
            return new FileInputStream("src/main/resources/config.properties");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public DatabaseConfiguration() throws IOException{
        this.properties.load(configFile());
    }

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(properties.getProperty("DB_URL"));
        dataSourceBuilder.username(properties.getProperty("DB_USERNAME"));
        dataSourceBuilder.password(properties.getProperty("DB_PASSWORD"));
        return dataSourceBuilder.build();
    }
}
