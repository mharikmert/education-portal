package com.fikirtepe.app.Service.ServiceImplementation;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Repository.UserRepository;
import com.fikirtepe.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//service layer between user and controller
@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).isPresent()? userRepository.findById(id).get(): null;
    }

    @Override
    public User findByLastName(String lastname) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user){
        userRepository.delete(user);
    }

    @Override
    public boolean verifyUser(User user) {
        User inDb = findById(user.getId());
        return (inDb.getPassword().equals(user.getPassword()));
    }
}
