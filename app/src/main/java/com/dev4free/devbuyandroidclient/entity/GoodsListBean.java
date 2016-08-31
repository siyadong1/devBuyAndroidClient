package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/8/31.
 */
public class GoodsListBean {


    private String image;
    private String description;
    private String current_price;
    private String price;
    private String sales_volume;
    private String items_id;

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(String sales_volume) {
        this.sales_volume = sales_volume;
    }

    public GoodsListBean() {
    }

    public GoodsListBean(String current_price, String description, String image, String items_id, String price, String sales_volume) {
        this.current_price = current_price;
        this.description = description;
        this.image = image;
        this.items_id = items_id;
        this.price = price;
        this.sales_volume = sales_volume;
    }
}
