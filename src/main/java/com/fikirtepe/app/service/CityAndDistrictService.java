package com.fikirtepe.app.service;

import com.fikirtepe.app.model.City;
import com.fikirtepe.app.model.District;

import java.util.List;

public interface CityAndDistrictService {
    List<District> findAllDistricts(Long cityPlateNo);
    List<City> findAllCities();
}
