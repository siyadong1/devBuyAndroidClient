package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/9.
 */
public class Goods {

    private int picPath;
    private String shopName;
    private String description;
    private String realPrice;
    private String salesPrice;
    private String saledAmount;

    public int getPicPath() {
        return picPath;
    }

    public void setPicPath(int picPath) {
        this.picPath = picPath;
    }

    public String getSaledAmount() {
        return saledAmount;
    }

    public void setSaledAmount(String saledAmount) {
        this.saledAmount = saledAmount;
    }

    public Goods(String description, String realPrice, String saledAmount, String salesPrice, int picPath) {
        this.description = description;
        this.realPrice = realPrice;
        this.saledAmount = saledAmount;
        this.salesPrice = salesPrice;
        this.picPath = picPath;
    }

    public Goods(String description, String realPrice, String saledAmount, String salesPrice) {
        this.description = description;
        this.realPrice = realPrice;
        this.saledAmount = saledAmount;
        this.salesPrice = salesPrice;

    }



    public Goods(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
