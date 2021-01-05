package com.complexica.travelplanner.weather.api.model.city;

import lombok.Data;

@Data
public class City {
    private Integer id;
    private String name;
    private CityCoordonations coord;
    private String country;
    private Integer timezone;
    private Long sunrise;
    private Long sunset;
}
