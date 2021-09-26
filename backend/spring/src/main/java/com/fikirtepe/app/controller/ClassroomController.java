package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Section;
import com.fikirtepe.app.service.ClassroomService;
import com.fikirtepe.app.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    private final SectionService sectionService;

    @Autowired
    public ClassroomController(ClassroomService classroomService, SectionService sectionService){
        this.classroomService = classroomService;
        this.sectionService = sectionService;
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

    @PostMapping("/assignLecture")
    public ResponseEntity<Section> assignLecture(@RequestBody Section section){
        return ResponseEntity.ok(sectionService.createSection(section));
    }

}
