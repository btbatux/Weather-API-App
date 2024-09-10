package com.btbatux.weather.dto;

public record Values

        (Double temperature,
         String time,
         Double cloudBase,
         Double cloudCover,
         Double   cloudCeiling,
         Double dewPoint,
         Double freezingRainIntensity,
         Double humidity,
         Double precipitationProbability,
         Double pressureSurfaceLevel,
         Double rainIntensity,
         Double sleetIntensity,
         Double snowIntensity,
         Double temperatureApparent,
         Double uvHealthConcern,
         Double uvIndex,
         Double visibility,
         Double weatherCode,
         Double windDirection,
         Double windGust,
         Double windSpeed) {
}
 