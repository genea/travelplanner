package com.complexica.travelplanner.weather.api.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

@Data
public class ForecastRain {
    @JsonProperty("3h")
    private Float threeHours;
}
