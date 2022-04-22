package com.fikirtepe.app.controller;

import com.fikirtepe.app.model.Term;
import com.fikirtepe.app.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TermController {

    private final TermService termService;
    @Autowired
    public TermController(TermService termService){
       this.termService = termService;

    }
    @GetMapping("/terms")
    public ResponseEntity<List<Term>> getTerms(){
        return ResponseEntity.ok(termService.getTerms());
    }

    @PostMapping( "/terms")
    public ResponseEntity<Term> addTerm(@RequestBody Term term){
        return ResponseEntity.ok(termService.createTerm(term));
    }
    @GetMapping("/terms/current")
    public ResponseEntity<Term> getCurrentTerm(){
        return ResponseEntity.ok(termService.getCurrentTerm());
    }
    @PostMapping("/updateTerms")
    public ResponseEntity<List<Term>> updateTerms(@RequestBody List<Term> terms){
        return ResponseEntity.ok(termService.updateTerms(terms));
    }
    @PostMapping("/terms/{id}/activate")
    public ResponseEntity<?> activateTerm(@PathVariable int id){
            termService.activateTerm(id);
            if(termService.getCurrentTerm().getId() == id){
                return ResponseEntity.ok("Term activated");
            }
            else {
                return ResponseEntity.ok("Term not activated");
            }
        }
}
