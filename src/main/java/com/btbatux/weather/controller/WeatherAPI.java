package com.btbatux.weather.controller;

import com.btbatux.weather.controller.validation.CityNameConstraint;
import com.btbatux.weather.dto.WeatherDto;
import com.btbatux.weather.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
public class WeatherAPI {

    private final WeatherService weatherService;

    public WeatherAPI(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/{city}")
    @RateLimiter(name = "user-limit")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("city") @Valid @CityNameConstraint @NotBlank String city) {

        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));

    }
}


