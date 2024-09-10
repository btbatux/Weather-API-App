package com.btbatux.weather.dto;

public record WeatherResponse
        (Location location,
         Data data,
         Values values) {
}
