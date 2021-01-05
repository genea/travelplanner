package com.complexica.travelplanner.service;

import com.complexica.travelplanner.config.AppConfig;
import com.complexica.travelplanner.entities.city.ForecastedCity;
import com.complexica.travelplanner.entities.dto.CityDto;
import com.complexica.travelplanner.models.TravelPlan;
import com.complexica.travelplanner.repository.ForecastedCityRepository;
import com.complexica.travelplanner.repository.ItineraryCitiesInMemoryStore;
import com.complexica.travelplanner.weather.api.WeatherRestApi;
import com.complexica.travelplanner.weather.api.model.forecast.ForecastResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForecastService {
    private final ForecastTransactorService forecastTransactorService;
    private final ForecastedCityRepository forecastedCityRepository;
    private final WeatherRestApi weatherRestApi;
    private final ItineraryCitiesInMemoryStore itineraryCitiesInMemoryStore;
    private final AppConfig appConfig;
    private final ConvertorService convertorService;


    public void getForecastInfo(TravelPlan travelPlan) {
        Optional<ForecastedCity> city = forecastedCityRepository.findByCityAndCountryCode(travelPlan.getCity(), travelPlan.getCountryCode());
        if (city.isEmpty()) {
            requestForecast(travelPlan);
        } else if (cityEntityIsOld(city.get())) {
            updateForecastInformation(travelPlan, city.get());
        } else {
            useInformationFromCache(travelPlan, city.get());
        }
    }

    public void requestForecast(TravelPlan travelPlan) {
        log.info("The travel plan is not found in Db, make request to the api");
        ForecastResponse forecastResponse = weatherRestApi.getCityForecast(travelPlan.getCity());
        ForecastedCity forecastedCity = forecastTransactorService.saveCityForecastInformations(forecastResponse);
        List<CityDto> cityForecastInfo = convertorService.convert(travelPlan, forecastedCity);
        itineraryCitiesInMemoryStore.addCities(cityForecastInfo);

    }

    public void updateForecastInformation(TravelPlan travelPlan, ForecastedCity forecastedCity) {
        log.info("The travel plan" + travelPlan + "is old, make request to the api");
        ForecastResponse forecastResponse = weatherRestApi.getCityForecast(travelPlan.getCity());
        forecastTransactorService.updateForecastInformations(forecastResponse, forecastedCity);
        List<CityDto> cityForecastInfo = convertorService.convert(travelPlan, forecastedCity);
        itineraryCitiesInMemoryStore.addCities(cityForecastInfo);
    }

    public void useInformationFromCache(TravelPlan travelPlan, ForecastedCity forecastedCity) {
        log.info("Use data from db for travel plan" + travelPlan);
        List<CityDto> cityForecastInfo = convertorService.convert(travelPlan, forecastedCity);
        itineraryCitiesInMemoryStore.addCities(cityForecastInfo);
    }

    public Boolean cityEntityIsOld(ForecastedCity forecastedCity) {
        long createdDate = forecastedCity.getCreatedDate();
        long modifiedDate = forecastedCity.getModifiedDate();
        return (createdDate > 0 && System.currentTimeMillis() - createdDate >= appConfig.entityMaxAge)
                || (modifiedDate > 0 && System.currentTimeMillis() - modifiedDate >= appConfig.entityMaxAge);
    }
}
