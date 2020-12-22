package com.fikirtepe.app.Repository;

import com.fikirtepe.app.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    void createUser(User user);
}
