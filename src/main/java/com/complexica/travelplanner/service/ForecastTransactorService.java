package com.complexica.travelplanner.service;

import com.complexica.travelplanner.entities.city.ForecastInformation;
import com.complexica.travelplanner.entities.city.ForecastedCity;
import com.complexica.travelplanner.repository.ForecastInformationRepository;
import com.complexica.travelplanner.repository.ForecastedCityRepository;
import com.complexica.travelplanner.weather.api.model.city.City;
import com.complexica.travelplanner.weather.api.model.forecast.ForecastClouds;
import com.complexica.travelplanner.weather.api.model.forecast.ForecastProperties;
import com.complexica.travelplanner.weather.api.model.forecast.ForecastRain;
import com.complexica.travelplanner.weather.api.model.forecast.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastTransactorService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ForecastedCityRepository forecastedCityRepository;
    private final ForecastInformationRepository forecastInformationRepository;

    @Transactional
    public ForecastedCity saveCityForecastInformations(ForecastResponse forecastResponse) {
        City city = forecastResponse.getCity();
        ForecastedCity forecastedCity = new ForecastedCity(city.getName(), city.getId(), city.getCountry(), System.currentTimeMillis());
        List<ForecastInformation> forecastInformation = mapForecastInformationToCity(forecastResponse.getList(), forecastedCity);

        entityManager.persist(forecastedCity);

        return mergeChanges(forecastedCity, forecastInformation);
    }

    @Transactional
    public ForecastedCity updateForecastInformations(ForecastResponse forecastResponse, ForecastedCity forecastedCity){
        List<ForecastInformation> forecastInformation = mapForecastInformationToCity(forecastResponse.getList(), forecastedCity);
        entityManager.detach(forecastedCity);
        forecastedCity.getForecastInformations().clear();
        return mergeChanges(forecastedCity, forecastInformation);
    }

    private List<ForecastInformation> mapForecastInformationToCity(List<ForecastProperties> forecastPropertiesList, ForecastedCity forecastedCity){
        return forecastPropertiesList.stream().map(forecastProperties -> {
            ForecastRain forecastRain = forecastProperties.getRain();
            Float rain = forecastRain != null ? forecastRain.getThreeHours() : null;
            ForecastClouds forecastClouds = forecastProperties.getClouds();
            Integer clouds = forecastClouds != null ? forecastClouds.getAll() : null;
            ForecastInformation forecastInformation = new ForecastInformation(forecastProperties.getMain().getTemp(), rain, clouds, forecastProperties.getDt());
            forecastInformation.setCity(forecastedCity);
            return forecastInformation;
        }).collect(Collectors.toUnmodifiableList());
    }

    private ForecastedCity mergeChanges(ForecastedCity forecastedCity, List<ForecastInformation> forecastInformation){
        forecastInformation.forEach(forecastedCity::addForecastInformation);
        return entityManager.merge(forecastedCity);
    }
}
