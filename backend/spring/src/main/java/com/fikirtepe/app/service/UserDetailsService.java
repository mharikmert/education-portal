package com.fikirtepe.app.service;

import com.fikirtepe.app.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    UserDetails loadUserByUsername(String username)
        throws UserNotFoundException;
}
