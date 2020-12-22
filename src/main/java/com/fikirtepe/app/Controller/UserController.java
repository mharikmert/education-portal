package com.fikirtepe.app.Controller;

import com.fikirtepe.app.Model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/api/users")
    public void createUser(@RequestBody User user){
        System.out.println(user.toString());
    }
}
