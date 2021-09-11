package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    void deleteStudent(Student student);
    Student findStudentById(Long id);
    Class findClassById(Long id);
    List<Student> findAll();
    List<Lecture> findLecturesById(Long id);
    List<Teacher> findTeachersById(Long id);

}
