package com.fikirtepe.app.Service;

import com.fikirtepe.app.Model.User;
import java.util.List;



public interface UserService {
    User findById(long id);
    User findByLastName(String lastname);
    List<User> findAllUsers();
    void createUser(User user);
    void delete(User user);
    boolean verifyUser(User user);


}
