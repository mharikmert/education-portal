package com.fikirtepe.app.Configuration;

import com.fikirtepe.app.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;
    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //commence method overwritten to avoid unauth as default
        http.httpBasic().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
        http

                .authorizeRequests().antMatchers( "/", "/register", "/login", "/js/**","/css/**", "/assets-img/**","/registration/**").permitAll()
                .anyRequest().authenticated();
                /* TO DO
                * Authentication config with logout process
                * Role assignments
                * Error page and its configuration for unauthenticated users
                * */


    }

    //authorizes our users -> user detail config is in auth service
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }
    //password encoder bean
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}