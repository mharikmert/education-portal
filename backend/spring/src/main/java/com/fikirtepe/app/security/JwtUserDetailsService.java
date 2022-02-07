package com.fikirtepe.app.security;

import com.fikirtepe.app.exception.UserNotFoundException;
import com.fikirtepe.app.model.User;
import com.fikirtepe.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            throw new UserNotFoundException("User is not found");
        }
        try{
            User user = userService.findByUserName(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())
                    .authorities(user.getRoles())
                    .build();
        }catch (UserNotFoundException ex){
            logger.info("user is not found in user detail service " + ex);
            return null;
        }
    }
}
