package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;

import java.util.List;

public interface LectureService {
    Lecture findLectureByName(String name);
    List<Class> findClassesByLectureName(String name);
    List<Teacher> findTeachersByLectureName(String name);
}
