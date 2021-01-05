package com.complexica.travelplanner.entities.itinerary;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Entity(name = "ItineraryStep")
@Table(name = "itinerary_step")
public class ItineraryStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    private String cityName;
    private String countryCode;
    private Float temperature;
    private String recommendations;
    private Integer clouds;
    private Float rain;
    private Long dateTime;

    public ItineraryStep(Itinerary itinerary, String cityName, String countryCode, Float temperature, String recommendations, Integer clouds, Float rain, Long dateTime) {
        this.itinerary = itinerary;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.recommendations = recommendations;
        this.clouds = clouds;
        this.rain = rain;
        this.dateTime = dateTime;
    }

    private ItineraryStep() {
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Float getRain() {
        return rain;
    }

    public void setRain(Float rain) {
        this.rain = rain;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getDate(){
       return Instant.ofEpochMilli(dateTime).atZone(ZoneId.systemDefault()).toLocalDateTime().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItineraryStep)) return false;
        ItineraryStep that = (ItineraryStep) o;
        return cityName.equals(that.cityName) && countryCode.equals(that.countryCode)  && dateTime.equals(that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, countryCode, dateTime);
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
