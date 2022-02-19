package com.fikirtepe.app.service.teacherServiceImplementation;

import com.fikirtepe.app.model.Role;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.repository.TeacherRepository;
import com.fikirtepe.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {
    private final TeacherRepository teacherRepository;
    @Autowired
    public TeacherServiceImplementation(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacher.setType("Öğretmen");
        teacher.setRoles(Collections.singletonList(Role.ROLE_TEACHER));
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findTeacherById(id);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
}
