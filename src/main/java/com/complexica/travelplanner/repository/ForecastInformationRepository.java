package com.complexica.travelplanner.repository;

import com.complexica.travelplanner.entities.city.ForecastInformation;
import com.complexica.travelplanner.entities.dto.CityDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastInformationRepository extends CrudRepository<ForecastInformation, Long> {

//    public List<CityDto> getCityForecastInformation();
}
