package com.fikirtepe.app.Service.ServiceImplementation;

import com.fikirtepe.app.Exceptions.UserNotFoundException;
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
    public User findUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByLastName(String lastname) {
        User tempUser = new User();
        tempUser.setLast_name(lastname);
        for(User user: userRepository.findAll())
            if(user.getLast_name().equals(lastname))
                return user;
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id){
       User userInDb = userRepository.findById(id).orElse(null);
       assert userInDb != null;
       userRepository.delete(userInDb);
    }

    @Override
    public boolean verifyUser(User user) {
        User inDb = findUser(user.getId());
        return (inDb.getPassword().equals(user.getPassword()));
    }
}
