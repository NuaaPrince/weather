package com.xavier.springboot.weather.service;

import com.xavier.springboot.weather.util.XmlBuilder;
import com.xavier.springboot.weather.vo.City;
import com.xavier.springboot.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @description
 * @author: XavierWang
 * @create: 2019-03-18 00:22
 **/
@Service
public class CityDataServiceImpl implements CityDataService {
    /**
     * 获取City列表
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<City> listCity() throws Exception {
        // 读取XML文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) !=null) {
            buffer.append(line);
        }

        br.close();

        // XML转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }
}
