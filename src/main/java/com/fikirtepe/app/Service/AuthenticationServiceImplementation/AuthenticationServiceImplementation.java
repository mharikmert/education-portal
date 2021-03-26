package com.fikirtepe.app.Service.AuthenticationServiceImplementation;

import com.fikirtepe.app.Error.Error;
import com.fikirtepe.app.Exceptions.UserNotFoundException;
import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.AuthenticationService;
import com.fikirtepe.app.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImplementation.class);

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<?> authenticate(String authorization){
        //set id and password from returned array
        String [] httpBasicAuthPayload = parseHttpBasicPayload(authorization);
        String id = httpBasicAuthPayload[0];
        String password = httpBasicAuthPayload[1];

        //creates a meaningful error body
        Error error = new Error(400, "validation error", "/api/auth");
        Map<String, String> validationErrors = new HashMap<>();

        //null check
        if(id == null || password == null){
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //determine the user
        Long userId = Long.parseLong(id);
        User user = new User(userId, password);

        try{
            //checks user credentials
            if(userService.verifyUser(user)){
                // TO DO
                // user should be authorized to access other endpoints
                return ResponseEntity.status(HttpStatus.OK).build();
            }else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        //maybe unauthorized is enough for
        }catch(UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception ex){
            logger.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //parse auth info from the client side -> Basic username:password
    @Override
    public String [] parseHttpBasicPayload(String authorization){
        if(authorization != null && authorization.toLowerCase().startsWith("basic")){
            String base64Credentials = authorization.substring("Basic".length()).trim(); // -> encodedId:encodedPassword
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials); // -> decodedId:decodedPassword
            String credentials = new String(credDecoded, StandardCharsets.UTF_8); // set to the string
            return credentials.split(":",2); // arr[0] -> id, arr[1] -> password
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            throw new UserNotFoundException("User is not found");
        }
        long id = Long.parseLong(username);

        try{
            User user = userService.findUser(id);
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())
                    .authorities(user.getRole())
                    .build();
        }catch (UserNotFoundException ex){
            return null;
        }
    }
}
