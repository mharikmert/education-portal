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
    public List<Classroom> getClassrooms() {
       return classroomRepository.findAll();
    }

    @Override
    public Classroom createClassroom(Classroom classRef){
        return classroomRepository.save(classRef);
    }

    @Override
    public Classroom getClassroomByName(String name) {
        return classroomRepository.findClassroomByName(name);
    }

    @Override
    public Classroom getClassroomById(Long id) {
       return classroomRepository.findClassroomById(id);
    }

    @Override
    public Set<Student> getStudentsByClassroomName(String classroomName){
        return classroomRepository.findClassroomByName(classroomName).getStudents();
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
