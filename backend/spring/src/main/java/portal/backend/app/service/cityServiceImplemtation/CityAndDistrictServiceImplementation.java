package portal.backend.app.service.cityServiceImplemtation;

import portal.backend.app.model.City;
import portal.backend.app.model.District;
import portal.backend.app.repository.CityRepository;
import portal.backend.app.repository.DistrictRepository;
import portal.backend.app.service.CityAndDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CityAndDistrictServiceImplementation implements CityAndDistrictService {

    DistrictRepository districtRepository;

    @Autowired
    public void setDistrictRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    private CityRepository cityRepository;

    @Autowired
    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<District> findAllDistricts(Long cityPlateNo) {
        return districtRepository.findAllByCityPlateNo(cityPlateNo);
    }

    @Override
    public List<City> findAllCities() {
        // return entityManager.createQuery("select cityName from
        // City").getResultList();
        return cityRepository.findAll();
    }
}
