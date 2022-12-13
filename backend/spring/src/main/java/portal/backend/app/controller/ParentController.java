package portal.backend.app.controller;

import portal.backend.app.model.Parent;
import portal.backend.app.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    public ResponseEntity<List<Parent>> getParents() {
        return ResponseEntity.ok(parentService.getParents());
    }

    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        return ResponseEntity.ok(parentService.createParent(parent));
    }
}
