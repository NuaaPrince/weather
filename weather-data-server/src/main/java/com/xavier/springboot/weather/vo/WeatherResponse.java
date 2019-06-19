package com.xavier.springboot.weather.vo;

import java.io.Serializable;

/**
 * @description
 * @author: XavierWang
 * @create: 2019-03-17 12:52
 **/
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = -2607796295603877709L;
    private Weather data;
    private Integer status;
    private String desc;

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
