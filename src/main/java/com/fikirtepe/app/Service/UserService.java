package com.fikirtepe.app.Service;

import com.fikirtepe.app.Model.User;
import java.util.List;

public interface UserService {
    User findUser(long id);
    List<User> findAllUsers();
    void createUser(User user);
    void deleteUser(Long id);
    boolean verifyUser(User user);
    void save(User user);
}
