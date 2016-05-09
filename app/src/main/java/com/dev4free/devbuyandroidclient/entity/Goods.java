package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/9.
 */
public class Goods {

    private String shopName;
    private String description;
    private String realPrice;
    private String salesPrice;


    public Goods(String description, String realPrice, String salesPrice, String shopName) {
        this.description = description;
        this.realPrice = realPrice;
        this.salesPrice = salesPrice;
        this.shopName = shopName;
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
