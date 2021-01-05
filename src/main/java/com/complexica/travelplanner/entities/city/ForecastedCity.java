package com.complexica.travelplanner.entities.city;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ForecastedCity")
@Table(name = "city")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ForecastedCity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String city;

    @NaturalId
    private Integer cityId;

    private String countryCode;

    private Long timestamp;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ForecastInformation> forecastInformations = new HashSet<>();

    private ForecastedCity(){}

    public ForecastedCity(String city, Integer cityId, String  countryCode, Long timestamp){
        this.city = city;
        this.cityId = cityId;
        this.countryCode = countryCode;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Set<ForecastInformation> getForecastInformations() {
        return forecastInformations;
    }

    public void setForecastInformations(Set<ForecastInformation> forecastInformations) {
        this.forecastInformations = forecastInformations;
    }

    public void addForecastInformation(ForecastInformation forecastInformation){
        forecastInformations.add(forecastInformation);
        forecastInformation.setCity(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForecastedCity)) return false;
        ForecastedCity that = (ForecastedCity) o;
        return Objects.equals(cityId, that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }
}
