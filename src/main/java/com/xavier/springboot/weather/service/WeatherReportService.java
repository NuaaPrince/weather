package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.vo.Weather;

/**
 * @description 天气预报服务接口
 * @author: XavierWang
 * @create: 2019-03-18 12:52
 **/
public interface WeatherReportService {

    /**
     * 根据城市ID查询天气信息
     *
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
