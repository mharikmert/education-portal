package com.fikirtepe.app.service;

import com.fikirtepe.app.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserById(long id);
    List<User> getUsers();
    User createUser(User user);
    void deleteUser(Long id);
    User updateUser(Long id, User user);
    void save(User user);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
}
