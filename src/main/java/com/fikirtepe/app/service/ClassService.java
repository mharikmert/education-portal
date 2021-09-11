package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;

import java.util.List;

public interface ClassService {
    Class createClass(Class classRef);
    Class findClassByName(String className);
    List<Student> findAllStudents(String className);
    List<Teacher> findAllTeachers(String className);
    void deleteClass(Class classRef);
    void deleteClassByName(String name);
}
