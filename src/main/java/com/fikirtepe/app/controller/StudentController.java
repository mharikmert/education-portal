package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("api/students")
    public List<Student> getStudents(){
        return studentService.findAllStudents();
    }
    @PostMapping("api/students")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }


}
