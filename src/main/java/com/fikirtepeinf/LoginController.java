package com.fikirtepeinf;

import com.fikirtepeinf.Model.User;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fikirtepeinf.DAO.JDBC.UserJdbcImplementation;
@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestParam MultiValueMap<String, String> map , HttpServletResponse response) throws IOException {

        System.out.println(map);
        User user = new User(map.getFirst("tc"), map.getFirst("password"));
        UserJdbcImplementation a = new UserJdbcImplementation();
        List<User> list = new ArrayList<>();
        list = a.allUsers();
        for (User value : list) {
            System.out.println(value.getTc());
        }System.out.println(
                a.checkInfo(user)
        );

    }

}
