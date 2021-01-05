package com.complexica.travelplanner.service;

import com.complexica.travelplanner.entities.city.ForecastInformation;
import com.complexica.travelplanner.entities.city.ForecastedCity;
import com.complexica.travelplanner.entities.dto.CityDto;
import com.complexica.travelplanner.entities.itinerary.Itinerary;
import com.complexica.travelplanner.entities.itinerary.ItineraryStep;
import com.complexica.travelplanner.models.TravelPlan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Maybe a better name is required
@Service
public class ConvertorService {

    public List<CityDto> convert(TravelPlan travelPlan, ForecastedCity forecastedCity){
        List<ForecastInformation> forecastInformations = new ArrayList<>(forecastedCity.getForecastInformations());
        return forecastInformations.stream()
                .filter(forecastInformation -> forecastInformation.getForecastedDay().equals(travelPlan.getDate()))
                .map(forecastInformation -> new CityDto(
                        forecastedCity.getCity(),
                        forecastedCity.getCountryCode(),
                        forecastInformation.getTemperature(),
                        forecastInformation.getClouds(),
                        forecastInformation.getRain(),
                        Instant.ofEpochMilli(forecastInformation.getDateTime()),
                        forecastInformation.getRecommendations()
                ))
                .collect(Collectors.toList());
    }

    public List<ItineraryStep> convert(Itinerary itinerary, List<CityDto> cityDtos){
        return cityDtos.stream().map(cityDto -> {
            ItineraryStep itineraryStep = new ItineraryStep(
                    itinerary,
                    cityDto.getCityName(),
                    cityDto.getCountryCode(),
                    cityDto.getTemperature(),
                    cityDto.getRecommendations(),
                    cityDto.getClouds(),
                    cityDto.getRain(),
                    cityDto.getDate().toEpochMilli()
            );
            itinerary.addItineraryStep(itineraryStep);
            return itineraryStep;
        }).collect(Collectors.toList());
    }
}
