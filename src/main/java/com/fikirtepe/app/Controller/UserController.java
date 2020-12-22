package com.fikirtepe.app.Controller;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Repository.UserRepository;
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
    public void createUser(@RequestBody User user){
        user.setName("hilmi");
        user.setSurname("arikmert");

        fikirtepeService.createUser(user);
        System.out.println(user.toString());
    }
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(fikirtepeService.getUsers());
    }
}