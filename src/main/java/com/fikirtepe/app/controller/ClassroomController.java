package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    @Autowired
    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }
    @GetMapping
    public ResponseEntity<List<Classroom>> getClassrooms(){
        return ResponseEntity.ok(classroomService.findAllClassrooms());
    }
    @GetMapping("/byName")
    public ResponseEntity<Classroom> getClassroomByName(@RequestParam("name") String name){
       return ResponseEntity.ok(classroomService.findClassroomByName(name));
    }
    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom){
        return ResponseEntity.ok(classroomService.createClassroom(classroom));
    }

}
