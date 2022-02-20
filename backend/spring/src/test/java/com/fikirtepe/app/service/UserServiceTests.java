package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Role;
import com.fikirtepe.app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    @Rollback
    public void testCreateUser() {
        //create a user object with all the fields in one line
        User user = new User(1L, "username", "password", "firstname", "lastname", "555 555 55 55",
                "test@gmail.com", "Ogrenci", "testCity", "testDistrict", false, List.of(Role.ROLE_STUDENT));

        userService.createUser(user);

        User user1 = userService.getUserById(user.getId());
        assert user1 != null;
        Assertions.assertEquals(user.getUsername(), user1.getUsername());
        Assertions.assertEquals(user.getPassword(), user1.getPassword());
        Assertions.assertEquals(user.getFirstName(), user1.getFirstName());
        Assertions.assertEquals(user.getLastName(), user1.getLastName());
    }

    @Test
    @Rollback
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("username");
        userService.save(user);
        User user1 = userService.getUserByUsername(user.getUsername());
        assert user1 != null;
        Assertions.assertEquals(user.getUsername(), user1.getUsername());

    }

    @Test
    @Rollback
    public void testGetAllUsers() {
        User user = new User();
        user.setUsername("username");
        userService.save(user);
        List<User> users = userService.getUsers();
        assert users.contains(user);
    }

    @Test
    @Rollback
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        userService.save(user);
        Assertions.assertNotNull(userService.getUserById(user.getId()));
        userService.deleteUser(user.getId());
        User user2 = userService.getUserByUsername(user.getUsername());
        Assertions.assertNull(user2);

    }

    @Test
    @Rollback
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        userService.save(user);
        Assertions.assertNotNull(userService.getUserById(user.getId()));
        Assertions.assertEquals(userService.getUserByUsername(user.getUsername()).getUsername(), user.getUsername());
        user.setUsername("username2");
        userService.updateUser(user.getId(), user);
        User user2 = userService.getUserByUsername(user.getUsername());
        assert user2 != null;
        Assertions.assertEquals(user.getUsername(), user2.getUsername());
    }

}