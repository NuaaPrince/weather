package com.xavier.springboot.weather.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @description 天气信息
 * @author: XavierWang
 * @create: 2019-03-17 12:37
 **/
public class Weather implements Serializable {
    private static final long serialVersionUID = -4196587279252611716L;

    private String city; //城市
    private String aqi; //空气指数
    private String ganmao; //感冒
    private String wendu; //温度
    private Yeaterday yesterday; //昨日天气
    private List<Forecast> forecast; //未来天气

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public Yeaterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yeaterday yesterday) {
        this.yesterday = yesterday;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
}
