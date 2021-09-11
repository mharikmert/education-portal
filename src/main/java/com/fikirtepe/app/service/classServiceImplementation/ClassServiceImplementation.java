package com.fikirtepe.app.service.classServiceImplementation;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.repository.ClassRepository;
import com.fikirtepe.app.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImplementation implements ClassService {

    private final ClassRepository classRepository;
    @Autowired
    public ClassServiceImplementation(ClassRepository classRepository){
        this.classRepository = classRepository;
    }

    @Override
    public Class createClass(Class classRef){
        return classRepository.save(classRef);
    }

    @Override
    public Class findClassByName(String name) {
        return classRepository.findClassByName(name);
    }

    @Override
    public List<Student> findAllStudents(String className) {
        return classRepository.findClassByName(className).getStudents();
    }

    @Override
    public List<Teacher> findAllTeachers(String className) {
        return classRepository.findClassByName(className).getTeachers();
    }

    @Override
    public void deleteClass(Class classRef){
        classRepository.delete(classRef);
    }
    @Override
    public void deleteClassByName(String name){
        classRepository.deleteClassByName(name);
    }
}
