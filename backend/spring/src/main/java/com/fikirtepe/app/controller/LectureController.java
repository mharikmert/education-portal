package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    private final LectureService lectureService;
    @Autowired
    public LectureController(LectureService lectureService){
        this.lectureService = lectureService;
    }
    @GetMapping
    public ResponseEntity<List<Lecture>> getLectures(){
        return ResponseEntity.ok(lectureService.findAllLectures());
    }
    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture){
        return ResponseEntity.ok(lectureService.createLecture(lecture));
    }
}
