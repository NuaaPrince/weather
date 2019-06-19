package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.vo.City;
import com.xavier.springboot.weather.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description
 * @author: XavierWang
 * @create: 2019-06-18 23:43
 **/
@FeignClient(name = "weather-zuul-server")
public interface DataClient {

    @GetMapping("/city/cities")
    List<City> listCity() throws Exception;

    @GetMapping("/data/weather/cityId/{cityId}")
    WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) ;
}
