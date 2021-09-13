package com.fikirtepe.app.service.classroomServiceImplementation;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.repository.ClassroomRepository;
import com.fikirtepe.app.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClassroomServiceImplementation implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    @Autowired
    public ClassroomServiceImplementation(ClassroomRepository classroomRepository){
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<Classroom> findAllClassrooms() {
       return classroomRepository.findAll();
    }

    @Override
    public Classroom createClassroom(Classroom classRef){
        return classroomRepository.save(classRef);
    }

    @Override
    public Classroom findClassroomByName(String name) {
        return classroomRepository.findClassroomByName(name);
    }

    @Override
    public Set<Student> findAllStudents(String className) {
        return classroomRepository.findClassroomByName(className).getStudents();
    }

    @Override
    public Set<Teacher> findAllTeachers(String className) {
        return classroomRepository.findClassroomByName(className).getTeachers();
    }

    @Override
    public void deleteClassroom(Classroom classRef){
        classroomRepository.delete(classRef);
    }
    @Override
    public void deleteClassroomByName(String name){
        classroomRepository.deleteClassroomByName(name);
    }
}
