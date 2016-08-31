package com.dev4free.devbuyandroidclient.entity;

import java.io.Serializable;

/**
 * Created by syd on 2016/8/27.
 */
public class ShoppingCarItems implements Serializable {


    /**
     * image : http://www.dev4free.com/devbuy/java/images/itemsPicture/20160821170548.jpg
     * price : 2799
     * items_num : 1
     * itemsname : OPPO R9
     * current_price : 2499
     * description : 4GB+64GB 玫瑰金 全网通4G手机 双卡双待
     */

    private String image;
    private String price;
    private String items_num;
    private String items_id;
    private String itemsname;
    private String current_price;
    private String description;
    private String cart_id;
    private String inventory ;

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
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

    public String getItems_num() {
        return items_num;
    }

    public void setItems_num(String items_num) {
        this.items_num = items_num;
    }

    public String getItemsname() {
        return itemsname;
    }

    public void setItemsname(String itemsname) {
        this.itemsname = itemsname;
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

    public ShoppingCarItems() {
    }

    public ShoppingCarItems(String current_price, String description, String image, String inventory, String itemsname, String price, String items_num,String items_id) {
        this.current_price = current_price;
        this.description = description;
        this.image = image;
        this.inventory = inventory;
        this.itemsname = itemsname;
        this.price = price;
        this.items_num = items_num;
        this.items_id = items_id;
    }

    @Override
    public String toString() {
        return "ShoppingCarItems{" +
                "cart_id='" + cart_id + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", items_num='" + items_num + '\'' +
                ", itemsname='" + itemsname + '\'' +
                ", current_price='" + current_price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
