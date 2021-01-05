package com.complexica.travelplanner.weather.api.model.forecast;

import com.complexica.travelplanner.weather.api.model.city.City;
import lombok.Data;

import java.util.List;

@Data
public class ForecastResponse {
    private  Integer cod;
    private  Integer message;
    private  Integer cnt;
    private List<ForecastProperties> list;
    private City city;

}
