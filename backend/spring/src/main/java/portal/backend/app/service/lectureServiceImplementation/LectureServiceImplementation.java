package portal.backend.app.service.lectureServiceImplementation;

import portal.backend.app.model.Classroom;
import portal.backend.app.model.Lecture;
import portal.backend.app.model.Teacher;
import portal.backend.app.repository.LectureRepository;
import portal.backend.app.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LectureServiceImplementation implements LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureServiceImplementation(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public List<Lecture> getLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture getLectureById(long id) {
        return lectureRepository.findLectureById(id);
    }

    @Override
    public Lecture getLectureByName(String name) {
        return lectureRepository.findLectureByName(name);
    }

    @Override
    public void updateLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    @Override
    public void deleteLecture(long id) {
        lectureRepository.delete(getLectureById(id));
    }

}
