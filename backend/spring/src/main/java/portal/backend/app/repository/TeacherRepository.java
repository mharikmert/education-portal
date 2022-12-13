package portal.backend.app.repository;

import portal.backend.app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherById(Long id);
}
