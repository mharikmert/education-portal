package portal.backend.app.service;

import portal.backend.app.model.Lecture;
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
public class LectureServiceTests {
    @Autowired
    private LectureService lectureService;

    @Test
    @Rollback
    public void testCreateLecture() {
        Lecture lecture = new Lecture();
        lecture.setName("test");
        lectureService.createLecture(lecture);
        Lecture lecture1 = lectureService.getLectureById(lecture.getId());
        Assertions.assertNotNull(lecture1);
        Assertions.assertEquals(lecture.getId(), lecture1.getId());
        Assertions.assertEquals(lecture.getName(), lecture1.getName());

    }

    @Test
    @Rollback
    public void testUpdateLecture() {
        Lecture lecture = new Lecture();
        lecture.setName("test");
        lectureService.createLecture(lecture);
        lecture.setName("test2");
        lectureService.updateLecture(lecture);
        Assertions.assertEquals(lecture.getName(), lectureService.getLectureById(lecture.getId()).getName());
        Assertions.assertEquals(lecture.getId(), lectureService.getLectureById(lecture.getId()).getId());
    }

    @Test
    @Rollback
    public void testDeleteLecture() {
        Lecture lecture = new Lecture();
        lecture.setName("test");
        lectureService.createLecture(lecture);
        lectureService.deleteLecture(lecture.getId());
        Assertions.assertNull(lectureService.getLectureById(lecture.getId()));
    }

    @Test
    @Rollback
    public void testGetLectureById() {
        Lecture lecture = new Lecture();
        lecture.setName("test");
        lectureService.createLecture(lecture);
        Assertions.assertNotNull(lectureService.getLectureById(lecture.getId()));
        Assertions.assertEquals(lecture.getName(), lectureService.getLectureById(lecture.getId()).getName());
        Assertions.assertEquals(lecture.getId(), lectureService.getLectureById(lecture.getId()).getId());
    }

    @Test
    @Rollback
    public void testGetLectures() {
        Lecture lecture = new Lecture();
        lecture.setName("test");
        lectureService.createLecture(lecture);
        Assertions.assertNotNull(lectureService.getLectures());
        Assertions.assertFalse(lectureService.getLectures().isEmpty());
    }

    @Test
    @Rollback
    public void testGetLectureByName() {
        Lecture lecture = new Lecture();
        lecture.setName("test");
        lectureService.createLecture(lecture);
        Assertions.assertNotNull(lectureService.getLectureByName(lecture.getName()));

    }
}
