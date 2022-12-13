package portal.backend.app.controller;

import portal.backend.app.model.Lecture;
import portal.backend.app.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping
    public ResponseEntity<List<Lecture>> getLectures() {
        return ResponseEntity.ok(lectureService.getLectures());
    }

    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        return ResponseEntity.ok(lectureService.createLecture(lecture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lecture> deleteLecture(@PathVariable Long id) {
        try {
            lectureService.deleteLecture(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
