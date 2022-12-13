package portal.backend.app.repository;

import portal.backend.app.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByCityPlateNo(Long cityPlateNo);
}
