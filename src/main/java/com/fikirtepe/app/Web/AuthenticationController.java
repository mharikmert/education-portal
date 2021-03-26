package com.fikirtepe.app.Web;

import com.fikirtepe.app.Service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private AuthenticationService authenticationService;
    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }


    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authHandler(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        try{
            return authenticationService.authenticate(authorization);
        }
        catch(Exception ex){
            logger.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
