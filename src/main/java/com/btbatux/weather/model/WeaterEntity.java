package com.btbatux.weather.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class WeaterEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String requestedCityName;

    private String cityName;

    private Double temperature;

    private LocalDateTime updatedTime; //en son yapılan sorgu zamanı

    private LocalDateTime responseLocalTime; //hava ölçüm tarihi


    public WeaterEntity() {
    }

    public WeaterEntity(String id, String requestedCityName, String cityName,  Double temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    public WeaterEntity(String requestedCityName, String cityName, Double temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.temperature = temperature;
        this.responseLocalTime = responseLocalTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }
}
