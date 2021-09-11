package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher findTeacherById(Long id);
    List<Teacher> findAll();
    List<Class> findClassesById(Long id);
}
