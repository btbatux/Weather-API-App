package com.btbatux.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getrestTemplate() {
        return new RestTemplate();  //Ben konteynırda rest tamplate oluştur ve sakla
    }
}
