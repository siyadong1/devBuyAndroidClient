package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/8.
 * 城市列表
 */
public class City  {

    private String cityName;
    private String initial;


    public City(String cityName, String initial) {
        this.cityName = cityName;
        this.initial = initial;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", initial='" + initial + '\'' +
                '}';
    }
}
