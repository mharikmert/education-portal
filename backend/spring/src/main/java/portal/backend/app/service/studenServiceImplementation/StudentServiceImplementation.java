package portal.backend.app.service.studenServiceImplementation;

import portal.backend.app.model.*;
import portal.backend.app.repository.StudentRepository;
import portal.backend.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setType("Öğrenci");
        student.setRoles(Collections.singletonList(Role.ROLE_STUDENT));
        return studentRepository.save(student);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.delete(getStudentById(id));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
