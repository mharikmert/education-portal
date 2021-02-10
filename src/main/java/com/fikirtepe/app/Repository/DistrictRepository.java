package com.fikirtepe.app.Repository;

import com.fikirtepe.app.Model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Long> {
    List<District> findAllByCityPlateNo(Long cityPlateNo);
}
