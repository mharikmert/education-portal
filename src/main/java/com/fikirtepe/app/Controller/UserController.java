package com.fikirtepe.app.Controller;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.FikirtepeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    private FikirtepeService fikirtepeService;

    @Autowired
    public void setFikirtepeService(FikirtepeService fikirtepeService) {
        this.fikirtepeService = fikirtepeService;
    }

    //user info is taken from the post request and user creates
    @RequestMapping( value = "/api/register", method = RequestMethod.POST)
    public void createUser(@RequestBody User user){

        if(fikirtepeService.findById(user.getId()) == null)
            fikirtepeService.createUser(user);

        else System.out.println("User is already exist");
    }

    //get the whole users in system database
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(fikirtepeService.getUsers());
    }

    //check to user info that comes from login screen
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public void checkUser(@RequestBody User user, HttpServletResponse response) throws IOException {
        System.out.println(user.toString());
        //find the user acc to id
        User temp = fikirtepeService.findById(user.getId());

        //nullity check and also password validation
        if(temp != null && temp.getPassword().equals(user.getPassword())) {
            System.out.println("User is verified !");
            //take the user inside and redirect the page
            response.sendRedirect("../text/web_menu.html");
        }
        else {
            //unauthorized error as response and message
            response.sendError(401,"user is not verified");
        }
    }


}