package com.fikirtepe.app.service.lectureServiceImplementation;

import com.fikirtepe.app.model.Class;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.repository.LectureRepository;
import com.fikirtepe.app.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImplementation implements LectureService {
    private final LectureRepository lectureRepository;
    @Autowired
    public LectureServiceImplementation(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }
    @Override
    public Lecture findLectureByName(String name) {
        return lectureRepository.findLectureByName(name);
    }

    @Override
    public List<Class> findClassesByLectureName(String name) {
        return lectureRepository.findLectureByName(name).getClasses();
    }

    @Override
    public List<Teacher> findTeachersByLectureName(String name) {
        return lectureRepository.findLectureByName(name).getTeachers();
    }
}
