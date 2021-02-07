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
    public User findUser(long id) throws UserNotFoundException{
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new UserNotFoundException("User not found");
        return user;
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
       userRepository.delete(findUser(id));
    }

    @Override
    public boolean verifyUser(User user) {
        try{
            User inDb = findUser(user.getId());
            return (inDb.getPassword().equals(user.getPassword()));
        } catch(UserNotFoundException ex){
            return false;
        }
    }
}
