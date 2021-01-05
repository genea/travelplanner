package com.complexica.travelplanner.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryDto {
    private List<CityDto> cities;
}
