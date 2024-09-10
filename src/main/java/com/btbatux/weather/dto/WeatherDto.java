package com.btbatux.weather.dto;
/*
 * record sınıfları genellikle DTO'lar için tercih edilir,
 * çünkü bu sınıflar sadece veri taşımak amacıyla kullanılır ve
 * record yapısının minimal, okunabilir bir söz dizimi sağlar.
 * Özellikle API yanıtları veya servisler arası veri taşımada
 * çok işlevseldir.
 * */

import com.btbatux.weather.model.WeaterEntity;

import java.time.LocalDateTime;

public record WeatherDto(
        String cityName,
        Double temperature,
        LocalDateTime updatedTime,
        LocalDateTime responseLocalTime) {





    public static WeatherDto convert(WeaterEntity from) {
        return new WeatherDto(from.getCityName(), from.getTemperature(), from.getUpdatedTime(), from.getResponseLocalTime());
    }

}
