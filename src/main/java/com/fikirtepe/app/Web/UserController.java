package com.fikirtepe.app.Web;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;
    @Autowired
    public void setFikirtepeService(UserService userService) {
        this.userService = userService;
    }

    //user info is taken from the post request and user creates
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void createUser(@RequestBody User user, HttpServletResponse response) throws IOException {

        if(userService.findById(user.getId()) == null) {
            userService.createUser(user);
            System.out.println(user.toString());
        }
        else response.sendError(409,"user is already exist");
    }

    //get the whole users in system database
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
    //get user with the given id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    //check to user info that comes from login screen
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void checkUser(@RequestBody User user, HttpServletResponse response) throws IOException {
        if(user.getId() == 0 || user.getPassword() == null) response.sendError(401, "user info is missing");
        else if(userService.verifyUser(user)){
            System.out.println("User is verified !");
        }
        else {
            //unauthorized error as response and message
            response.sendError(401,"user is not verified");
        }
    }
}