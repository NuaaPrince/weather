package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.vo.WeatherResponse;

/**
 * @description 天气数据服务接口
 * @author: XavierWang
 * @create: 2019-03-17 12:52
 **/
public interface WeatherDataService {
    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

}
