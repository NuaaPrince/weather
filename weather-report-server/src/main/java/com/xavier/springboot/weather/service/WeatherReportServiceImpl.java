package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.vo.Forecast;
import com.xavier.springboot.weather.vo.Weather;
import com.xavier.springboot.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 天气预报服务实现
 * @author: XavierWang
 * @create: 2019-03-18 23:33
 **/
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherReportServiceImpl.class);

    @Autowired
    private DataClient dataClient;
    /**
     * 根据城市ID查询天气信息
     *
     * @param cityId
     * @return
     */
    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse weatherResponse = dataClient.getWeatherByCityId(cityId);
        Weather weather = weatherResponse.getData();

        return weather;
    }
}
