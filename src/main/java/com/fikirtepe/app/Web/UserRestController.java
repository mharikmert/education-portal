package com.fikirtepe.app.Web;

import com.fikirtepe.app.Error.Error;
import com.fikirtepe.app.Exceptions.UserNotFoundException;
import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Repository.UserRepository;
import com.fikirtepe.app.Service.EmailService;
import com.fikirtepe.app.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //user info is taken from the post request and user creates
    //@ResponseStatus(HttpStatus.CREATED) // returned 201 if process is succeeded
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user,
                                             HttpServletResponse response) throws IOException {
        logger.info(user.toString());
        try{
            userService.findUser(user.getId());
            return ResponseEntity.status(409).build();
        }
        catch(UserNotFoundException ex){
            /*
            should refactor
            */
            userService.createUser(user);
            emailService.sendRegistrationReceivedMail(user);

            //create resource location
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            //return 201 from uri location
            return ResponseEntity.created(location).build();
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //handles with login requests
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST)
    public ResponseEntity<?> validateUser(@RequestBody User user)
    {
        //error sample usage if user id is empty
        Error error = new Error(400, "validation error", "/api/login");
        Map<String, String> validationErrors = new HashMap<>();

        if(user.getId() == 0)
        {
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); // id 0, 400 bad request
        }

        else if(userService.verifyUser(user))
        {
            return ResponseEntity.status(HttpStatus.OK).build(); // returns 200 ok
        }
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //returns all the users
    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    //returns user with the given id parameter
    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return Optional
                .ofNullable(userService.findUser(id))
                .map(user -> ResponseEntity.ok().body(user)) // 200 ok
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 not found
    }

    //saves users that is approved by the admin in registration queue
    @RequestMapping(
            value = "/approveUser/{id}",
            method = RequestMethod.POST)
    public void approveUser(@PathVariable long id) {
        User user = userService.findUser(id);
        user.setApproved(true);
        userService.save(user);
        emailService.sendRegistrationApprovedMail(user);
    }

    //deletes user that is rejected by the admin in registration queue
    @RequestMapping(
            value = "/rejectUser/{id}",
            method = RequestMethod.POST)
    public void rejectUser(@PathVariable long id){
        User user = userService.findUser(id);
        userService.deleteUser(id);
        emailService.sendRegistrationRejectedMail(user);
    }

    //deletes a user with id
    @RequestMapping(
            value = "/delete/{id}",
            //defining explicit mapping to avoid 405 not supported error -> https://www.baeldung.com/spring-request-method-not-supported-405
            method = {RequestMethod.GET, RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            //find the user with the given id and delete
            userService.findUser(id);
            userService.deleteUser(id);
            return ResponseEntity.ok().body("User is successfully deleted");

        }catch(UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not found to delete"); // 404 user not found
        }catch(Exception ex){
            //other exceptions are caught, build an internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}