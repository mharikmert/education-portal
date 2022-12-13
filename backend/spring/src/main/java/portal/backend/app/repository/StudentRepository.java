package portal.backend.app.repository;

import portal.backend.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentById(Long id);
}
