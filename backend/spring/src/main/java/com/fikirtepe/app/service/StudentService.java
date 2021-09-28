package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Student createStudent(Student student);
    Student save(Student student);
    void deleteStudent(Student student);
    Student findStudentById(Long id);
    Classroom findClassroomsById(Long id);
    List<Student> findAllStudents();
    Set<Lecture> findLecturesById(Long id);
    Set<Teacher> findTeachersById(Long id);

}
