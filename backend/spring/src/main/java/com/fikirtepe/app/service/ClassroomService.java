package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface ClassroomService {
    List<Classroom> getClassrooms();
    Classroom createClassroom(Classroom classRef);
    Classroom getClassroomByName(String classroomName);
    Set<Student> getStudentsByClassroomName(String classroomName);
    Classroom getClassroomById(Long id);
    void deleteClassroom(Classroom classRef);
    void deleteClassroomByName(String name);
}
