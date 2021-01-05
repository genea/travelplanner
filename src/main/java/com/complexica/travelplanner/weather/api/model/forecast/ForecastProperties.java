package com.complexica.travelplanner.weather.api.model.forecast;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Optional;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ForecastProperties {
    private Long dt;
    private ForecastMainInformation main;
    private ForecastClouds clouds;
    private ForecastWind wind;
    private Integer visibility;
    private Float pop;
    private ForecastRain rain;
    private ForecastSnow snow;
    private ForecastSys sys;
    private String dtTxt;
}
