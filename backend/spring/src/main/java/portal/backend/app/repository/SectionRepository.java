package portal.backend.app.repository;

import portal.backend.app.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionsByClassroomId(long id);

    Section findSectionByClassroomIdAndDayAndStartingTime(long id, String day, String startingTime);

    Section findSectionById(long id);
}
