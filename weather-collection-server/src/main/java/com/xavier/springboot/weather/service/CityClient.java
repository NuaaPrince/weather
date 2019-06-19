package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @description
 * @author: XavierWang
 * @create: 2019-06-18 23:43
 **/
@FeignClient(name = "weather-city-server")
public interface CityClient {

    @GetMapping("/cities")
    List<City> listCity() throws Exception;
}
