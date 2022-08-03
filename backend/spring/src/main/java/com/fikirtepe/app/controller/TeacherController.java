package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.model.User;
import com.fikirtepe.app.service.EmailService;
import com.fikirtepe.app.service.LectureService;
import com.fikirtepe.app.service.TeacherService;
import com.fikirtepe.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final LectureService lectureService;
    private final EmailService emailService;
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public TeacherController(TeacherService teacherService, LectureService lectureService, EmailService emailService, UserService userService) {
        this.teacherService = teacherService;
        this.lectureService = lectureService;
        this.emailService = emailService;
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){

        try {
            Optional<User> user = userService.getUserByEmail(teacher.getEmail());

            if(user.isPresent()){
                return ResponseEntity.status(409).build();
            }

            else{
                Optional<Lecture> lecture = Optional.ofNullable(lectureService.getLectureByName(teacher.getLecture().getName()));

                if(lecture.isPresent()){
                    teacher.setLecture(lecture.get());
                }
                else{
                    teacher.setLecture(lectureService.createLecture(teacher.getLecture()));
                }
                teacher.setPassword("password");
                teacherService.createTeacher(teacher);
                emailService.sendRegistrationReceivedMail(teacher);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(teacher.getId())
                        .toUri();
                // location -> /api/teachers/{id}
                return ResponseEntity.created(location).build();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/temp")
    public ResponseEntity<List<Teacher>> createTeachers(@RequestBody List<Teacher> teachers){
       teachers.forEach(teacherService::createTeacher);
       return ResponseEntity.ok(teacherService.getTeachers());
    }
}


