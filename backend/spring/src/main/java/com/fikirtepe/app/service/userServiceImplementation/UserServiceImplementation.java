package com.fikirtepe.app.service.userServiceImplementation;

import com.fikirtepe.app.exception.UserNotFoundException;
import com.fikirtepe.app.model.User;
import com.fikirtepe.app.repository.UserRepository;
import com.fikirtepe.app.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//service layer between user and controller
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(long id) throws UserNotFoundException{
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new UserNotFoundException("User not found");
        return user;
    }

    @Override
//    @Secured(value = {"ROLE_ADMIN"})
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
//    @Secured(value = {"ROLE_ADMIN"})
    public void deleteUser(Long id){
       userRepository.delete(getUserById(id));
    }

    //create a method to update the user

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);

        if(userToUpdate == null)
            throw new UserNotFoundException("User is not found with id");

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setRoles(user.getRoles());
        userToUpdate.setEmail(user.getEmail());


        return userRepository.save(userToUpdate);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
