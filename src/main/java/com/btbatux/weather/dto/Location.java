package com.btbatux.weather.dto;

public record Location
        (String name,
         Double lat,
         Double lon,
         String type)   //city name
{}
