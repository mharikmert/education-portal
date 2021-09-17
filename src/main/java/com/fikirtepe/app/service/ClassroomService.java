package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface ClassroomService {
    List<Classroom> findAllClassrooms();
    Classroom createClassroom(Classroom classRef);
    Classroom findClassroomByName(String className);
    Classroom findClassroomById(Long id);
    Set<Student> findAllStudents(String className);
    Set<Teacher> findAllTeachers(String className);
    void deleteClassroom(Classroom classRef);
    void deleteClassroomByName(String name);
}
