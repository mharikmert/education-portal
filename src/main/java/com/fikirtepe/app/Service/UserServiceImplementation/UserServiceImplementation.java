package com.fikirtepe.app.Service.UserServiceImplementation;

import com.fikirtepe.app.Exceptions.UserNotFoundException;
import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Repository.UserRepository;
import com.fikirtepe.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUser(long id) throws UserNotFoundException{
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new UserNotFoundException("User not found");
        return user;
    }

    @Override
    @Secured(value = {"ROLE_ADMIN"})
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Secured(value = {"ROLE_ADMIN"})
    public void deleteUser(Long id){
       userRepository.delete(findUser(id));
    }

    @Override
    public boolean verifyUser(User user) {
        try{
            User inDb = findUser(user.getId());
            //check the password matches with encrypted password in database
            return passwordEncoder.matches(user.getPassword(), inDb.getPassword());
        } catch(UserNotFoundException ex){
            return false;
        }
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

}
