package com.xavier.springboot.weather.controller;

import com.xavier.springboot.weather.service.DataClient;
import com.xavier.springboot.weather.vo.City;
import com.xavier.springboot.weather.service.WeatherReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description 天气预报控制层
 * @author: XavierWang
 * @create: 2019-03-18 23:28
 **/
@RestController
@RequestMapping("/report")
public class WeatherReportController {
    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService weatherReportService;

    @Autowired
    private DataClient cityClient;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        model.addAttribute("title","老王的天气预报");
        model.addAttribute("cityId",cityId);

        List<City> cityList = cityClient.listCity();

        model.addAttribute("cityList",cityList);

        model.addAttribute("report",weatherReportService.getDataByCityId(cityId));

        return new ModelAndView("weather/report","reportModel", model);
    }
}
