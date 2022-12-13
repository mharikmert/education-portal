package portal.backend.app.service;

import portal.backend.app.model.City;
import portal.backend.app.model.District;

import java.util.List;

public interface CityAndDistrictService {
    List<District> findAllDistricts(Long cityPlateNo);

    List<City> findAllCities();
}
