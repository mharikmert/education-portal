package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Teacher;
import com.fikirtepe.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        return ResponseEntity.ok(teacherService.findAll());
    }
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }
    @PostMapping("/temp")
    public ResponseEntity<List<Teacher>> createTeachers(@RequestBody List<Teacher> teachers){
       teachers.forEach(teacherService::createTeacher);
       return ResponseEntity.ok(teacherService.findAll());
    }
}


