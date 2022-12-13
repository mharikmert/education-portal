package portal.backend.app.service;

import portal.backend.app.model.Teacher;
import portal.backend.app.repository.TeacherRepository;
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
public class TeacherServiceTests {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @Rollback
    public void testCreateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Matthew");
        teacher.setLastName("Perry");
        teacherService.createTeacher(teacher);
        Teacher teacher1 = teacherRepository.findTeacherById(teacher.getId());
        Assertions.assertEquals(teacher.getFirstName(), teacher1.getFirstName());
        Assertions.assertEquals(teacher1.getType(), "Öğretmen");
    }

    @Test
    @Rollback
    public void testGetTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Matthew");
        teacher.setLastName("Perry");
        teacherService.createTeacher(teacher);
        Assertions.assertNotNull(teacherService.getTeacherById(teacher.getId()));
        Assertions.assertEquals(teacher.getFirstName(), teacherService.getTeacherById(teacher.getId()).getFirstName());
    }

    @Test
    @Rollback
    public void testGetTeachers() {
        Teacher teacher = new Teacher();
        teacherService.createTeacher(teacher);
        Assertions.assertNotNull(teacherService.getTeachers());
        Assertions.assertFalse(teacherService.getTeachers().isEmpty());
    }

}
