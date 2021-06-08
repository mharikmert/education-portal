package com.fikirtepe.app.web;

import com.fikirtepe.app.service.CityAndDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CityRestController {

    CityAndDistrictService cityService;

    @Autowired
    public void setCityService(CityAndDistrictService cityService) {
        this.cityService = cityService;
    }

    //returns whole districts with the same plate no
    @RequestMapping(
            value = "/cities/{cityPlateNo}/districts",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllDistricts(@PathVariable long cityPlateNo){
        return ResponseEntity.ok().body(cityService.findAllDistricts(cityPlateNo));
    }

    @RequestMapping(
            value = "/cities",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getCities(){
        return ResponseEntity.ok().body(cityService.findAllCities());
    }
}
