package portal.backend.app.controller;

import portal.backend.app.model.Classroom;
import portal.backend.app.model.Section;
import portal.backend.app.service.ClassroomService;
import portal.backend.app.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    private final SectionService sectionService;

    @Autowired
    public ClassroomController(ClassroomService classroomService, SectionService sectionService) {
        this.classroomService = classroomService;
        this.sectionService = sectionService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getClassrooms() {
        return ResponseEntity.ok(classroomService.getClassrooms());
    }

    @GetMapping("/byName")
    public ResponseEntity<Classroom> getClassroomByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(classroomService.getClassroomByName(name));
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        return ResponseEntity.ok(classroomService.createClassroom(classroom));
    }

    @PostMapping("/assignLecture")
    public ResponseEntity<?> assignLecture(@RequestBody Section section) {
        if (sectionService.getSectionByClassroomIdAndDayAndStartingTime(section.getClassroom().getId(),
                section.getDay(), section.getStartingTime()) == null) {
            if (section.getNumberOfHours() == 1) {
                sectionService.createSection(section);
                return ResponseEntity.status(HttpStatus.CREATED).body(section);
            } else if (section.getNumberOfHours() == 2) {
                sectionService.createSection(section);
                String startingTimeOfNextLecture = Integer.parseInt(section.getStartingTime().split(":")[0]) + 1
                        + ":00";
                Section nextSection = new Section(section.getId() + 1, section.getDay(), startingTimeOfNextLecture,
                        section.getNumberOfHours() - 1, section.getLecture(), section.getTeacher(),
                        section.getClassroom());
                sectionService.createSection(nextSection);
                return ResponseEntity.status(HttpStatus.CREATED).body(List.of(section, nextSection));
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid section");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Section is not empty");
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<List<Section>> getSchedule(@PathVariable long id) {
        return ResponseEntity.ok(sectionService.getSectionsByClassroomId(id));
    }

}
