package com.complexica.travelplanner.entities.city;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

@Entity(name = "ForecastInformation")
@Table(name = "forecast")
public class ForecastInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private ForecastedCity city;

    private Long dateTime;

    private Integer clouds;

    private Float temperature;

    private Float rain;

    private String recommendations;

    private String forecastedDay;

    private ForecastInformation(){}

    public ForecastInformation(Float temperature, Float rain, Integer clouds, Long dateTime){
        this.temperature = temperature;
        this.clouds = clouds;
        this.dateTime = dateTime;
        this.forecastedDay = getDayFromTimestamp(dateTime);
        this.rain = rain;
        this.recommendations = createRecommendations();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ForecastedCity getCity() {
        return city;
    }

    public void setCity(ForecastedCity city) {
        this.city = city;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    private String getDayFromTimestamp(Long dateTime){
        return Instant.ofEpochSecond(dateTime).atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    private String createRecommendations(){
        StringBuilder sb = new StringBuilder();
        if(temperature < 5) sb.append("Please take a coat \n");
        if(rain != null && rain > 0) sb.append("Please take a coat");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  ForecastInformation)) return false;
        ForecastInformation that = (ForecastInformation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getRecommendations() {
        return recommendations;
    }

    public Float getRain() {
        return rain;
    }

    public void setRain(Float rain) {
        this.rain = rain;
    }

    public String getForecastedDay() {
        return forecastedDay;
    }
}
