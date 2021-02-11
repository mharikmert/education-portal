package com.fikirtepe.app.Service;

import com.fikirtepe.app.Model.City;
import com.fikirtepe.app.Model.District;

import java.util.List;

public interface CityAndDistrictService {
    List<District> findAllDistricts(Long cityPlateNo);
    List<City> findAllCities();
}
