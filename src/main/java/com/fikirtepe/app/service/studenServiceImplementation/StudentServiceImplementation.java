package com.fikirtepe.app.service.studenServiceImplementation;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.repository.StudentRepository;
import com.fikirtepe.app.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    public StudentServiceImplementation(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public Student createStudent(Student student) {
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
    public Class findClassById(Long id) {
        return studentRepository.findStudentById(id).getStudentClass();
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Lecture> findLecturesById(Long id) {
        return studentRepository.findStudentById(id).getLectures();
    }

    @Override
    public List<Teacher> findTeachersById(Long id) {
        return studentRepository.findStudentById(id).getStudentClass().getTeachers();
    }
}
