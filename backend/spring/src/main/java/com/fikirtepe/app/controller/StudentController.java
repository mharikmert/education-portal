package com.fikirtepe.app.controller;

import com.fikirtepe.app.exception.UserNotFoundException;
import com.fikirtepe.app.model.*;
import com.fikirtepe.app.service.EmailService;
import com.fikirtepe.app.service.StudentService;
import com.fikirtepe.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;
    private final EmailService emailService;
    private final UserService userService;
    @Autowired
    public StudentController(StudentService studentService, EmailService emailService, UserService userService, PasswordEncoder passwordEncoder){
        this.studentService = studentService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    //creates a student
    //@ResponseStatus(HttpStatus.CREATED) // returned 201 if process is succeeded
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student,
                                        HttpServletResponse response) throws IOException {
        logger.info(student.toString());
        try{
            userService.getUserById(student.getId());
            return ResponseEntity.status(409).build();
        }
        catch(UserNotFoundException ex){
            student.setPassword("password");
            studentService.createStudent(student);
            emailService.sendRegistrationReceivedMail(student);

            //create resource location
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(student.getId())
                    .toUri();
            //return 201 from uri location
            return ResponseEntity.created(location).build();
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/{id}/assignClassroom")
    public ResponseEntity<Student> assignClassroom(@RequestBody Classroom classroom, @PathVariable long id){
        try {
            Student student = studentService.getStudentById(id);
            student.setClassroom(classroom);
            studentService.save(student);
            return ResponseEntity.ok(student);
        } catch (UserNotFoundException ex){
            throw new UserNotFoundException("User is not assigned to any classroom") ;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
