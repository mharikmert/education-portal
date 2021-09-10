package com.fikirtepe.app.controller;

import com.fikirtepe.app.exception.UserNotFoundException;
import com.fikirtepe.app.model.Role;
import com.fikirtepe.app.model.Term;
import com.fikirtepe.app.model.User;
import com.fikirtepe.app.repository.TermRepository;
import com.fikirtepe.app.service.EmailService;
import com.fikirtepe.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final EmailService emailService;
    private final UserService userService;
    private final TermRepository termRepository;

    @Autowired
    public UserRestController(EmailService emailService, UserService userService, TermRepository termRepository){
        this.emailService = emailService;
        this.userService = userService;
        this.termRepository = termRepository;

    }
    //user info is taken from the post request and user creates
    //@ResponseStatus(HttpStatus.CREATED) // returned 201 if process is succeeded
    @RequestMapping(
            value = "/users",
            method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user,
                                             HttpServletResponse response) throws IOException {
        logger.info(user.toString());
        try{
            userService.findUser(user.getId());
            return ResponseEntity.status(409).build();
        }
        catch(UserNotFoundException ex){
            user.setPassword("password");
            user.setType("STUDENT");
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
    //returns all the users
    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    //returns user with the given id parameter
    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return Optional
                .ofNullable(userService.findUser(id))
                .map(user -> ResponseEntity.ok().body(user)) // 200 ok
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 not found
    }

    @RequestMapping(
            path = "/users/by/username/{username}",
            method = RequestMethod.GET)
    public ResponseEntity<User> getUserWithUsername(@PathVariable("username") String username){
        return Optional
                .ofNullable(userService.findByUserName(username))
                .map( user -> ResponseEntity.ok().body(user))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/terms")
    public ResponseEntity<List<Term>> getTerms(){
        return ResponseEntity.ok(termRepository.findAll());
    }

    @PostMapping( "/terms")
    public ResponseEntity<Term> addTerm(@RequestBody Term term){
        termRepository.save(term);
        return ResponseEntity.ok(term);
    }

    //saves users that is approved by the admin in registration queue
    @RequestMapping(
            value = "/approveUser/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<User> approveUser(@PathVariable long id) {
        User user = userService.findUser(id);
        user.setApproved(true);
        userService.save(user);
        emailService.sendRegistrationApprovedMail(user);
        return ResponseEntity.ok(user);
    }

    //deletes user that is rejected by the admin in registration queue
    @RequestMapping(
            value = "/rejectUser/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<User> rejectUser(@PathVariable long id){
        User user = userService.findUser(id);
        userService.deleteUser(id);
        emailService.sendRegistrationRejectedMail(user);
        return ResponseEntity.ok(user);
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

    @RequestMapping(
            value = "/assignRole/{id}/{role}",
            method = RequestMethod.GET)
    public ResponseEntity<User> assignRole(@PathVariable long id,@PathVariable int role){
        User user = userService.findUser(id);
        switch (role){
            case 0 : user.setRoles(Collections.singletonList(Role.ROLE_USER)); break;
            case 1 : user.setRoles(Collections.singletonList(Role.ROLE_ADMIN)); break;
        }
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}