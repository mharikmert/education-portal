package portal.backend.app.repository;

import portal.backend.app.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findParentById(Long id);
}
