package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Long> {
    List<District> findAllByCityPlateNo(Long cityPlateNo);
}
