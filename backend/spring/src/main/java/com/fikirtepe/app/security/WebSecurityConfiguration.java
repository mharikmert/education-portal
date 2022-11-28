package com.fikirtepe.app.security;

import com.fikirtepe.app.repository.UserRepository;
import com.fikirtepe.app.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration  {

    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
    }

    @Value("${allowed.paths}") private String [] allowedPaths;
    @Value("${post.allowed.paths}") private String [] postAllowedPaths;
    @Value("${get.allowed.paths}") private String [] getAllowedPaths;
    @Value("${admin.allowed.paths}") private String [] adminAllowedPaths;
    @Value("${authorized.and.admin.allowed.paths}") private String [] authorizedAndAdminAllowedPaths;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // enable cors, disable csrf
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(allowedPaths).permitAll()
                .antMatchers(HttpMethod.POST, postAllowedPaths).permitAll()
                .antMatchers(HttpMethod.GET, getAllowedPaths).permitAll()
                .antMatchers(adminAllowedPaths).access("hasRole('ROLE_ADMIN')")
                .antMatchers(authorizedAndAdminAllowedPaths).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHORIZED')")

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login");

        http.authenticationManager(authManager(http, passwordEncoder()));

        return http.build();
    }

    //password encoder bean
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

}
