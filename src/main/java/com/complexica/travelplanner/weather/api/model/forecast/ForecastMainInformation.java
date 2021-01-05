package com.complexica.travelplanner.weather.api.model.forecast;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ForecastMainInformation {
    private Float temp;
    private Float feelsLike;
    private Float tempMin;
    private Float tempMax;
    private Integer pressure;
    private Integer seaLevel;
    private Integer grndLevel;
    private Integer humidity;
    private Float tempKf;
}
