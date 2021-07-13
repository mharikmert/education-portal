package com.fikirtepe.app.web;

import com.fikirtepe.app.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private AuthenticationService authenticationService;
    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

//    @CrossOrigin(origins = "https://localhost:4200")
    @RequestMapping(value = "/api/auth", method = RequestMethod.GET)
    public ResponseEntity<?> authHandler(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization){
        try{
            return authenticationService.authenticate(authorization);
        }
        catch(Exception ex){
            logger.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
