package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.service.LectureService;
import com.fikirtepe.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final LectureService lectureService;
    @Autowired
    public TeacherController(TeacherService teacherService, LectureService lectureService) {
        this.teacherService = teacherService;
        this.lectureService = lectureService;
    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){

        Lecture lecture = lectureService.getLectureByName(teacher.getLecture().getName());

        if(lecture == null)
            lectureService.createLecture(teacher.getLecture());
        else
            teacher.setLecture(lecture);

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
    }
    @PostMapping("/temp")
    public ResponseEntity<List<Teacher>> createTeachers(@RequestBody List<Teacher> teachers){
       teachers.forEach(teacherService::createTeacher);
       return ResponseEntity.ok(teacherService.getTeachers());
    }
}


