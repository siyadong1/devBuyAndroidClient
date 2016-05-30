package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/27.
 */
public class Main2GoodsAndName {

    private int image;
    private String name;


    public Main2GoodsAndName(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
