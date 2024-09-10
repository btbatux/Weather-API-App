package com.btbatux.weather.repository;

import com.btbatux.weather.model.WeaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeaterEntity, String> {

    Optional<WeaterEntity> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);

}
