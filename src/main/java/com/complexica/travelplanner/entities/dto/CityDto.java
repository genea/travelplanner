package com.complexica.travelplanner.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class CityDto {
    private String cityName;
    private String countryCode;
    private Float temperature;
    private Integer clouds;
    private Float rain;
    private Instant date;
    private String recommendations;
}
