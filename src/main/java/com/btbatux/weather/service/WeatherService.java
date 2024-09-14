package com.btbatux.weather.service;

import com.btbatux.weather.constants.Constanst;
import com.btbatux.weather.dto.WeatherDto;
import com.btbatux.weather.dto.WeatherResponse;
import com.btbatux.weather.model.WeaterEntity;
import com.btbatux.weather.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"weathers"})
public class WeatherService {


    //Repo bağımlılığını enjekte et
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }


    //Önce repo'yu sorgula yoksa apiye git, repoya kaydet tekrar geri buraya return et.
    @Cacheable(key ="#city")
    public WeatherDto getWeatherByCityName(String city) {
        Optional<WeaterEntity> weaterEntityOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);
        //Sorgu boş gelirse
        if (!weaterEntityOptional.isPresent()) {
            return WeatherDto.convert(getWeatherFromWeatherStack(city));
        }
        //en son sorgunun üzerinden 6 saatten fazla zaman geçtiyse true ol ve apiden güncel veri getir.
        if (weaterEntityOptional.get().getUpdatedTime().isBefore(LocalDateTime.now().minusHours(6))) {
            return WeatherDto.convert(getWeatherFromWeatherStack(city));
        }

        return WeatherDto.convert(weaterEntityOptional.get()); //return dto..
    }


    // Repo'da hava durumu bilgisi yok ise API'den çağır.
    private WeaterEntity getWeatherFromWeatherStack(String city) {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(Constanst.API_URL + city + Constanst.API_KEY, String.class); //String Json değeri

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(city, weatherResponse);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);}}



    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(cron = "0 0 0/6 * * ?")
    public void clearCache(){}



    //Sorgulanan şehrin hava durumu bilgisini database'e gönder.
    private WeaterEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");


        WeaterEntity weaterEntity = new WeaterEntity(city, weatherResponse.location().name(), weatherResponse.data().values().temperature(), LocalDateTime.now(), //sorgu atma tarihi
                LocalDateTime.parse(weatherResponse.data().time(), dateTimeFormatter)); //hava ölçüm tarihi
        return weatherRepository.save(weaterEntity);
    }
}
