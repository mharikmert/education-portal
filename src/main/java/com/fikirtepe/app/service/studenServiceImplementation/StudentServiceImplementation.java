package com.fikirtepe.app.service.studenServiceImplementation;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.repository.StudentRepository;
import com.fikirtepe.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository, PasswordEncoder passwordEncoder){
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setType("Öğrenci");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public Classroom findClassroomsById(Long id) {
        return studentRepository.findStudentById(id).getClassroom();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Set<Lecture> findLecturesById(Long id) {
        return studentRepository.findStudentById(id).getLectures();
    }

    @Override
    public Set<Teacher> findTeachersById(Long id) {
        return studentRepository.findStudentById(id).getClassroom().getTeachers();
    }
}
