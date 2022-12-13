package portal.backend.app.service;

import portal.backend.app.model.Lecture;

import java.util.List;

public interface LectureService {
    Lecture createLecture(Lecture lecture);

    List<Lecture> getLectures();

    Lecture getLectureById(long id);

    Lecture getLectureByName(String name);

    void updateLecture(Lecture lecture);

    void deleteLecture(long id);
}
