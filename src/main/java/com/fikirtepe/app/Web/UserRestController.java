package com.fikirtepe.app.Web;

import com.fikirtepe.app.Error.Error;
import com.fikirtepe.app.Exceptions.UserNotFoundException;
import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    UserService userService;
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
                                             HttpServletResponse response){
        System.out.println(user.toString());
        try{
            userService.findUser(user.getId());
            System.out.println("User is already exist");
            return ResponseEntity.created(new URI("/user/id")).build();
        }
        catch(UserNotFoundException ex){
            //userService.createUser(user);
            System.out.println(user.toString());
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return null;
    }
    //handles with login requests
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST)
    public ResponseEntity<?> checkUser(@RequestBody User user,
                                           HttpServletResponse response) throws IOException {
        //error sample usage if user id is empty
        Error error = new Error(400, "validation error", "/api/login");
        Map<String, String> validationErrors = new HashMap<>();
        if(user.getId() == 0){
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        else if(userService.verifyUser(user))
            return ResponseEntity.status(HttpStatus.OK).build(); // returns 200 ok
        return null;
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