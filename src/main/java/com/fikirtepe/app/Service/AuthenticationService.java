package com.fikirtepe.app.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticationService extends UserDetailsService{
    ResponseEntity<?> authenticate(String authorization);
    String [] parseHttpBasicPayload(String authorization);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
