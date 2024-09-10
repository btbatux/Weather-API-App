package com.btbatux.weather.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Constanst {
    public static String API_URL;
    public static String API_KEY;


    @Value("${weather-stack.api-url}")
    private String apiUrl;

    @Value("${weather-stack.api-key}")
    private String apiKey;



    @PostConstruct
    public void init() {
        API_URL = this.apiUrl;
        API_KEY = this.apiKey;
    }
}
