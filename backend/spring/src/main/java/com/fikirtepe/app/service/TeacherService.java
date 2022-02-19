package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher save(Teacher teacher);
    Teacher getTeacherById(Long id);
    List<Teacher> getTeachers();
}
