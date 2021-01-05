package com.complexica.travelplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${weather.forecast.path}")
    public String forecastPath;

    @Value("${weather.apy.key}")
    public String weatherApiKey;

    @Value("${entity.max.age.ms}")
    public long entityMaxAge = 60000;

}
