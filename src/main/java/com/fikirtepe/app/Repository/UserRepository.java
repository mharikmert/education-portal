package com.fikirtepe.app.Repository;

import com.fikirtepe.app.Model.User;
import java.util.List;

public interface UserRepository {
    void create(User user);
    List<User> findAll();
    User findById(long id);
}
