package portal.backend.app.controller;

import portal.backend.app.exception.UserNotFoundException;
import portal.backend.app.model.*;
import portal.backend.app.service.ClassroomService;
import portal.backend.app.service.EmailService;
import portal.backend.app.service.StudentService;
import portal.backend.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;
    private final ClassroomService classroomService;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService studentService, ClassroomService classroomService,
            EmailService emailService, UserService userService, PasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.userService = userService;
        this.emailService = emailService;
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    // creates a student
    @ResponseStatus(HttpStatus.CREATED) // returned 201 successfully created
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Optional<User> user = userService.getUserByEmail(student.getEmail());

            if (user.isPresent()) {
                return ResponseEntity.status(409).build();
            }

            else {
                student.setPassword("password");
                studentService.createStudent(student);
                emailService.sendRegistrationReceivedMail(student);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(student.getId())
                        .toUri();
                return ResponseEntity.created(location).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/assignClassroom")
    public ResponseEntity<?> assignClassroom(@RequestBody Classroom classroom, @PathVariable long id) {
        try {
            Student student = studentService.getStudentById(id);
            if (Objects.equals(student.getClassroom().getId(), classroom.getId())) {
                logger.info("Student is already assigned to the same classroom");
                return ResponseEntity.status(409).build();
            } else if (student.getClassroom() != null) {
                student.getClassroom().setClassroomSize(student.getClassroom().getClassroomSize() - 1);
                classroomService.updateClassroom(student.getClassroom());
            }

            student.setClassroom(classroom);
            studentService.save(student);

            classroom.setClassroomSize(classroom.getClassroomSize() + 1);
            classroomService.updateClassroom(classroom);

            return ResponseEntity.ok().build();

        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException("User is not assigned to any classroom");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
