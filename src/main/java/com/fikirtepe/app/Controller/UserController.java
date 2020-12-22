package com.fikirtepe.app.Controller;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.FikirtepeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private FikirtepeService fikirtepeService;

    @Autowired
    public void setFikirtepeService(FikirtepeService fikirtepeService) {
        this.fikirtepeService = fikirtepeService;
    }

    @RequestMapping( value = "/api/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
        user.setName("hilmi");
        user.setSurname("arikmert");

        fikirtepeService.createUser(user);
        return ResponseEntity.ok(user);
    }
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(fikirtepeService.getUsers());
    }

   @RequestMapping(value = "api/login", method = RequestMethod.POST)
   public void userControl(@RequestBody User user){
        User temp = fikirtepeService.findById(user.getId());
        System.out.println(temp.toString());
    }





}