package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
