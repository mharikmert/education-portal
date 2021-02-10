package com.fikirtepe.app.Web;

import com.fikirtepe.app.Service.CityServiceImplemtation.CityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CityRestController {

    CityServiceImplementation cityService;

    @Autowired
    public void setCityService(CityServiceImplementation cityService) {
        this.cityService = cityService;
    }

    //returns whole districts with the same plate no
    @RequestMapping(
            value = "/districts/{cityPlateNo}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllDistricts(@PathVariable long cityPlateNo){
        return ResponseEntity.ok().body(cityService.findAllDistricts(cityPlateNo));
    }
}
