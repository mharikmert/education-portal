package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Parent;
import com.fikirtepe.app.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentService parentService;
    @Autowired
    public ParentController(ParentService parentService){
        this.parentService = parentService;
    }
    @GetMapping
    public ResponseEntity<List<Parent>> getParents(){
        return ResponseEntity.ok(parentService.getParents());
    }
    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent){
        return ResponseEntity.ok(parentService.createParent(parent));
    }
}

