package com.fikirtepe.app.Service;

import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> authenticate(String authorization);
    String [] parseHttpBasicPayload(String authorization);
}
