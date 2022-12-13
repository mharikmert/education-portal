package portal.backend.app.service;

import portal.backend.app.model.Classroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class ClassroomServiceTests {
    @Autowired
    private ClassroomService classroomService;

    @Test
    public void testGetClassrooms() {
        Classroom classroom = new Classroom();
        classroom.setName("Test Classroom");
        classroomService.createClassroom(classroom);
        Assertions.assertEquals(1, classroomService.getClassrooms().size());
    }

    @Test
    public void testCreateClassroom() {
        Classroom classroom = new Classroom();
        classroom.setName("Test Classroom");
        classroomService.createClassroom(classroom);
        Assertions.assertNotNull(classroomService.getClassroomById(classroom.getId()));
        Assertions.assertEquals(1, classroomService.getClassrooms().size());
        Assertions.assertEquals("Test Classroom", classroomService.getClassroomById(classroom.getId()).getName());
    }

    @Test
    public void testGetClassroomByName() {
        Classroom classroom = new Classroom();
        classroom.setName("Test Classroom");
        classroomService.createClassroom(classroom);
        Classroom classroom2 = classroomService.getClassroomByName(classroom.getName());
        Assertions.assertEquals(classroom.getId(), classroom2.getId());

    }

    @Test
    public void testDeleteClassroom() {
        Classroom classroom = new Classroom();
        classroom.setName("Test Classroom");
        classroomService.createClassroom(classroom);
        classroomService.deleteClassroomByName(classroom.getName());
        Assertions.assertEquals(0, classroomService.getClassrooms().size());
        Assertions.assertNull(classroomService.getClassroomByName(classroom.getName()));
    }

}
