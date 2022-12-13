package portal.backend.app.repository;

import portal.backend.app.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findLectureById(Long id);

    Lecture findLectureByName(String name);
}
