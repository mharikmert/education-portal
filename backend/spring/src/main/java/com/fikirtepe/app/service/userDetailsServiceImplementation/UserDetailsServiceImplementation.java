package com.fikirtepe.app.service.userDetailsServiceImplementation;

import com.fikirtepe.app.exception.UserNotFoundException;
import com.fikirtepe.app.model.User;
import com.fikirtepe.app.repository.UserRepository;
import com.fikirtepe.app.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplementation.class);
    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username : " + username));

        UserDetails userDetails = org.springframework.security.core.userdetails
                .User.withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .build();

        logger.info("UserDetails : " + userDetails);

        return userDetails;
    }
}
