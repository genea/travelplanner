package com.complexica.travelplanner.weather.api;

import com.complexica.travelplanner.config.AppConfig;
import com.complexica.travelplanner.weather.api.model.forecast.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Component
public class WeatherRestApi {

    private final WebClient webClient;
    private final AppConfig appConfig;


    public ForecastResponse getCityForecast(String city) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(appConfig.forecastPath)
                            .queryParam("q", city)
                            .queryParam("appid", appConfig.weatherApiKey)
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ForecastResponse.class)
                    .toFuture().join();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
