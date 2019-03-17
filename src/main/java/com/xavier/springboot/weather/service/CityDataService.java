package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.vo.City;

import java.util.List;

/**
 * @description 城市数据服务接口
 * @author: XavierWang
 * @create: 2019-03-17 12:52
 **/
public interface CityDataService {

    /**
     * 获取City列表
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
