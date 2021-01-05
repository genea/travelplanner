package com.complexica.travelplanner.weather.api.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForecastSnow {
    @JsonProperty("3h")
    private Float threeHours;
}
