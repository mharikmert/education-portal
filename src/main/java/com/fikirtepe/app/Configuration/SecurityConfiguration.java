package com.fikirtepe.app.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                /* open sources path's for any user*/
                .antMatchers("/**/favicon.ico",
                        "/css/**",
                        "/js/**",
                        "/assets-img/**",
                        "/api/register/**",
                        "/api/cities/**",
                        "/registration/**", // to be able to access registration pages' static sources
                        "/", // main screen
                        "/register", // registration form
                        "/login").permitAll()
                //role restriction to avoid direct access by out users
                .antMatchers("menu").access("hasRole('USER')")
                /* pages authentication according to user roles*/
                //.antMatchers("/actuator/**").access("hasRole('ADMIN')")
                /* Request methods are accessed by just authenticated users*/
                .anyRequest().authenticated();

        /* unauthenticated users redirect login page by default*/
        http.formLogin()
                .loginPage("/login") //login path
                .loginProcessingUrl("/menu") //submit uri
                .failureForwardUrl("/login.html?loginFailed=true"); //authentication failure
        http.rememberMe().userDetailsService(userDetailsService); //remember me ability
//        http.httpBasic(); // enabling basic authentication
    }
}