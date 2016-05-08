package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/8.
 * 城市列表
 */
public class City  {


    private String symbol;
    private String name;


    public City(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public City() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
