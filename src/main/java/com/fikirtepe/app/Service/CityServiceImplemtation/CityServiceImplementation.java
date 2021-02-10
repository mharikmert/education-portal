package com.fikirtepe.app.Service.CityServiceImplemtation;

import com.fikirtepe.app.Model.District;
import com.fikirtepe.app.Repository.DistrictRepository;
import com.fikirtepe.app.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImplementation implements CityService {
    DistrictRepository districtRepository;

    @Autowired
    public void setDistrictRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }
    public List<District> findAllDistricts(Long cityPlateNo){
       return districtRepository.findAllByCityPlateNo(cityPlateNo);
    }
}
