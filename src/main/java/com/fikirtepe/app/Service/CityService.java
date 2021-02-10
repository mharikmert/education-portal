package com.fikirtepe.app.Service;

import com.fikirtepe.app.Model.District;

import java.util.List;

public interface CityService {
    List<District> findAllDistricts(Long cityPlateNo);
}
