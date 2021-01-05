package com.complexica.travelplanner.repository;

import com.complexica.travelplanner.entities.dto.CityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In memory store for itinerary steps
 */
@Repository
@RequiredArgsConstructor
public class ItineraryCitiesInMemoryStore {
    private List<CityDto> cities = new ArrayList<>();

    public void addCity(CityDto city) {
        cities.add(city);
    }

    public List<CityDto> getAllCities() {
        return cities;
    }

    public void addCities(List<CityDto> cities){
        this.cities.addAll(cities);
    }

    public void clearStore(){
        this.cities.clear();
    }
}
