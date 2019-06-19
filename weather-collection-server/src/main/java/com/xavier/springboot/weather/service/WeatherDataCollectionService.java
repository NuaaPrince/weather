package com.xavier.springboot.weather.service;

/**
 * @description 天气数据采集服务接口
 * @author: XavierWang
 * @create: 2019-03-19 23:57
 **/
public interface WeatherDataCollectionService {

    /**
     * 根据城市ID同步天气数据
     *
     * @param cityId
     * @return
     */
    void syncDataByCityId(String cityId);
}
