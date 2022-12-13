package portal.backend.app.controller;

import portal.backend.app.exception.UserNotFoundException;
import portal.backend.app.model.Role;
import portal.backend.app.model.Student;
import portal.backend.app.model.User;
import portal.backend.app.service.EmailService;
import portal.backend.app.service.StudentService;
import portal.backend.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final EmailService emailService;
    private final UserService userService;
    private final StudentService studentService;

    @Autowired
    public UserRestController(EmailService emailService, UserService userService, StudentService studentService) {
        this.emailService = emailService;
        this.userService = userService;
        this.studentService = studentService;

    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("Creating user: {}", user);
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Getting all users");
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("Getting user with id: {}", id);
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        Optional<Student> userAsStudent = Optional.ofNullable(studentService.getStudentById(id));
        if (user.isPresent()) {
            return userAsStudent.<ResponseEntity<User>>map(
                    student -> new ResponseEntity<>(student, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(user.get(), HttpStatus.OK));
        } else {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("Updating user with id: {}", id);
        Optional<User> oldUser = Optional.ofNullable(userService.getUserById(id));
        if (oldUser.isPresent()) {
            User newUser = userService.updateUser(id, user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }

    @GetMapping("/users/{id}/roles")
    public ResponseEntity<List<Role>> getRolesByUserId(@PathVariable Long id) {
        logger.info("Getting roles for user with id: {}", id);
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get().getRoles(), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }

    @PostMapping("/users/{id}/roles")
    public ResponseEntity<User> updateUserRoles(@PathVariable Long id, @RequestBody List<Role> roles) {
        logger.info("Adding role to user with id: {}", id);
        logger.info("Roles: {}", roles);
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isPresent()) {
            user.get().setRoles(roles);
            userService.save(user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }

    @RequestMapping(path = "/users/by/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable("username") String username) {
        return Optional
                .ofNullable(userService.getUserByUsername(username))
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // saves users that are approved by the admin in registration queue
    @RequestMapping(value = "/approveUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> approveUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        user.setApproved(true);
        userService.save(user);
        emailService.sendRegistrationApprovedMail(user);
        return ResponseEntity.ok(user);
    }

    // deletes user that is rejected by the admin in registration queue
    @RequestMapping(value = "/rejectUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> rejectUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(id);
        emailService.sendRegistrationRejectedMail(user);
        return ResponseEntity.ok(user);
    }
}