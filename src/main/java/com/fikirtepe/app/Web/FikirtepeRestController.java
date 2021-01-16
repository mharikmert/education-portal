package com.fikirtepe.app.Web;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.FikirtepeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FikirtepeRestController {

    FikirtepeService fikirtepeService;
    @Autowired
    public void setFikirtepeService(FikirtepeService fikirtepeService) {
        this.fikirtepeService = fikirtepeService;
    }

    //user info is taken from the post request and user creates
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        System.out.println(user.getId());
        if(fikirtepeService.findById(user.getId()).isEmpty()) {
            fikirtepeService.createUser(user);
            System.out.println(user.toString());
        }
        else System.out.println("User is already exist");
    }

    //get the whole users in system database
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(fikirtepeService.findAllUsers());
    }

    //check to user info that comes from login screen
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void checkUser(@RequestBody User user, HttpServletResponse response) throws IOException {
//        System.out.println(user.toString());
        //find the user acc to id

        //nullity check and also password validation
        if(fikirtepeService.verifyUser(user)){
            System.out.println("User is verified !");
            //take the user inside and redirect the page
//            response.sendRedirect("../text/web_menu.html");
        }
        else {
            //unauthorized error as response and message
            response.sendError(401,"user is not verified");
        }
    }
}