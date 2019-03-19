package com.xavier.springboot.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xavier.springboot.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: XavierWang
 * @create: 2019-03-17 13:03
 **/
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIME_OUT = 1800L; //1800 缓存失效时间

    @Autowired
    private RestTemplate restTemplate;

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

    /**
     * 根据城市ID来同步天气
     *
     * @param cityId
     */
    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
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
            //缓存没有，再调服务接口来获取
            logger.info("Redis don't has data");
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);


            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();

                //数据写入缓存
                ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
            }
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("Error!", e);
        }
        return resp;
    }

    /**
     * 把天气数据放在缓存
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String>  ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }
}
