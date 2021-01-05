package com.complexica.travelplanner.repository;

import com.complexica.travelplanner.entities.city.ForecastedCity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastedCityRepository extends CrudRepository<ForecastedCity, Long> {
    ForecastedCity findById(long id);
    Optional<ForecastedCity> findByCityAndCountryCode(String city, String countryCode);
}
