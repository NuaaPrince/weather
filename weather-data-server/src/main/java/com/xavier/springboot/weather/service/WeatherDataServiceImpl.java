package com.xavier.springboot.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xavier.springboot.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @description
 * @author: XavierWang
 * @create: 2019-03-17 13:03
 **/
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    private WeatherResponse doGetWeather(String uri) {
        String key = uri;
        String strBody = null;
        WeatherResponse resp = null;
        ObjectMapper mapper = new ObjectMapper();

        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        //先查缓存，缓存有的取缓存中的数据
        if (stringRedisTemplate.hasKey(key)) {
            strBody = ops.get(key);
            logger.info("Redis has data");
        } else {
            //缓存没有，抛出异常
            logger.info("Redis don't has data");
            throw new RuntimeException("Don't has data");
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("Error!", e);
        }
        return resp;
    }

}
